//
//  ViewModel + View.swift
//  iosApp
//
//  Created by Macbook on 18/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine

private class ObservableModel<StateObserved>: ObservableObject {
    @Published var stateObserved: StateObserved?
    
    init(statePublisher: AnyPublisher<StateObserved, Never>) {
        statePublisher
            .compactMap { $0 }
            .receive(on: DispatchQueue.main)
            .assign(to: &$stateObserved)
    }
}

public struct ObservingView<StateObserved, Content>: View where Content: View {
    @ObservedObject private var model: ObservableModel<StateObserved>
    
    private let content: (StateObserved) -> Content
    
    public init(statePublisher: AnyPublisher<StateObserved, Never>, @ViewBuilder content: @escaping (StateObserved) -> Content) {
        self.model = ObservableModel(statePublisher: statePublisher)
        self.content = content
    }
    
    public var body: some View {
        let view: AnyView
        if let viewState = self.model.stateObserved {
            view = AnyView(content(viewState))
        } else {
            view = AnyView(EmptyView())
        }
        
        return view
    }
}
