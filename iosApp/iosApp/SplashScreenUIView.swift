//
//  SplashScreenUIView.swift
//  iosApp
//
//  Created by Macbook on 15/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SplashScreenUIView: View {
    @State private var isActive  = false
    @State private var size = 0.8
    @State private var opaCity = 0.5
    
    var body: some View {
        ZStack{
            if self.isActive{
                LoginScreen()
            } else{
            
                LinearGradient(gradient: Gradient(stops: [
                    Gradient.Stop(color: Color(red: 0.33, green: 0.13, blue: 0.5), location: 0.00),
                    Gradient.Stop(color: Color(red: 0.64, green: 0.07, blue: 0.55), location: 0.61),
                    Gradient.Stop(color: Color(red: 0.76, green: 0.09, blue: 0.52), location: 1.00),
                ]),startPoint: UnitPoint(x: 1, y: 0),endPoint: UnitPoint(x: 0, y: 1)
                            
                )
                .edgesIgnoringSafeArea(.all)
                Image("Image").resizable().frame(width: 390,height: 390)
            }
        }.onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.5) {
                withAnimation {
                    self.isActive = true
                }
            }
        }
    }
}


struct SplashScreenUIView_Previews: PreviewProvider {
    static var previews: some View {
        SplashScreenUIView()
    }
}
