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
        
        Text("Hello World!!")
//        ObservingView(statePublisher: statePublisher(loginViewModel.viewStates())){ viewState in
//
//        }
//        .sheet(isPresented: $isForgotPresented) {
//            RestorePasswordUIView()
//        }
//        .sheet(isPresented: $isRegistrationPresented, content: {
//            SignUpUIView()
//        })
//        .sheet(isPresented: $isMainPresented, content: {
//            MainUIView()
//        })
//        .onReceive(sharePublisher(loginViewModel.viewActions())){ action in
//            switch (action){
//            case LoginAction.OpenForgotPasswordScreen():
//                isForgotPresented = true
//            case LoginAction.OpenRegistrationScreen():
//                isRegistrationPresented = true
//
//            case LoginAction.OpenMainFlow():
//                isMainPresented = true
//
//            default:
//                break
//            }
//        }
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
