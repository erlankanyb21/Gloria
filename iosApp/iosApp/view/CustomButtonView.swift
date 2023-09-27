//
//  CustomButtonView.swift
//  iosApp
//
//  Created by Macbook on 15/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CustomButtonView<Destination: View>: View {
    var title: String
    var backgroundGradient: LinearGradient
    var titleColor: Color
    var cornerRadius: CGFloat
    var width: CGFloat
    var destination: Destination
    
    var body: some View {
        NavigationLink(destination: destination) {
            HStack {
                Text(title).foregroundColor(titleColor)
            }
            .padding(.vertical, 10)
            .frame(width: width, height: 44, alignment: .center)
            .cornerRadius(5)
            .background(backgroundGradient) // Используем градиент как фон
            .cornerRadius(cornerRadius)
        }
    }
}

//                .frame(width: 343, height: 44, alignment: .center)

struct CustomButtonView_Previews: PreviewProvider {
    static var previews: some View {
        let backgroundGradient = LinearGradient(gradient: Gradient(stops: [
            Gradient.Stop(color: Color(red: 0.33, green: 0.13, blue: 0.5), location: 0.00),
            Gradient.Stop(color: Color(red: 0.64, green: 0.07, blue: 0.55), location: 0.61),
            Gradient.Stop(color: Color(red: 0.76, green: 0.09, blue: 0.52), location: 1.00),
        ]),
            startPoint: UnitPoint(x: 1, y: 0),
            endPoint: UnitPoint(x: 0, y: 1))
        
        return CustomButtonView(title: "Начать", backgroundGradient: backgroundGradient, titleColor: .white, cornerRadius: 6, width: 343, destination: MainUIView())
    }
}
