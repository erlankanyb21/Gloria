//
//  ActionButton.swift
//  iosApp
//
//  Created by Macbook on 18/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ActionButton: View {
    
    let title: String
    var enabled: Bool = true
    let height: CGFloat = 56
    let horizontalMargin: CGFloat = 16
    let action: () -> Void
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 40)
                .opacity(enabled ? 1.0 : 0.5)
                .overlay(
                    LinearGradient(
                    stops: [
                    Gradient.Stop(color: Color(red: 0.33, green: 0.13, blue: 0.5), location: 0.00),
                    Gradient.Stop(color: Color(red: 0.64, green: 0.07, blue: 0.55), location: 0.61),
                    Gradient.Stop(color: Color(red: 0.76, green: 0.09, blue: 0.52), location: 1.00),
                    ],
                    startPoint: UnitPoint(x: 1, y: 0),
                    endPoint: UnitPoint(x: 0, y: 1)
                    )
                    .clipShape(RoundedRectangle(cornerRadius: 40))
                )
            
            Text(title)
                .foregroundColor(.white)
                .font(Font.custom("SF Pro Text", size: 18)
                    .width(.standard))
        }.frame(width: 350, height: 40)
        .onTapGesture {
            action()
        }
    }
}

struct ActionButton_Previews: PreviewProvider {
    static var previews: some View {
        ActionButton(title: "Login"){
            
        }
    }
}
