//
//  LoginScreen.swift
//  iosApp
//
//  Created by Macbook on 18/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct LoginScreen: View {
    @State private var isForgotPresented = false
    @State private var isRegistrationPresented = false
    @State private var isMainPresented = false
    private let loginViewModel = LoginViewModel()
    var body: some View {
        NavigationView {
            VStack {
                ObservingView(statePublisher: statePublisher(loginViewModel.viewStates())) { viewState in
                      SignInUIView(viewState: viewState) { event in
                          loginViewModel.obtainEvent(viewEvent: event)
                      }
                  }
                NavigationLink(
                    destination: RestorePasswordUIView(),
                    isActive: $isForgotPresented,
                    label: {
                        EmptyView()
                    })
                    .opacity(0)
                
                NavigationLink(
                    destination: SignUpScreen(),
                    isActive: $isRegistrationPresented,
                    label: {
                        EmptyView()
                    })
                    .opacity(0)
                
                NavigationLink(
                    destination: MainUIView(),
                    isActive: $isMainPresented,
                    label: {
                        EmptyView()
                    })
                    .opacity(0)
            }
        }
        .onReceive(sharePublisher(loginViewModel.viewActions())) { action in
            switch action {
            case LoginAction.OpenForgotPasswordScreen():
                isForgotPresented = true
                
            case LoginAction.OpenRegistrationScreen():
                isRegistrationPresented = true
                
            case LoginAction.OpenMainFlow():
                isMainPresented = true
                
            default:
                break
            }
        }
    } // bodyView
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
