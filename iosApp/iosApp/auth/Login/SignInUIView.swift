//
//  SignInUIView.swift
//  iosApp
//
//  Created by Macbook on 15/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct SignInUIView: View {
    let viewState: LoginViewState
    let eventHandler: (LoginEvent) -> Void
    
    
    var body: some View {
        VStack{
            Spacer().frame(height: 40)
            Text("Привет \nс с возвращением!")
                .font(.system(size: 24))
                .multilineTextAlignment(.center)
                .foregroundColor(Color(red: 0.53, green: 0.09, blue: 0.53))
            
            Spacer().frame(height: 16)
            
            CommonTextField(hint: "Login", enabled: !viewState.isSending) { newValue in
                eventHandler(.PhoneNumberChanged(value: newValue))
            }
            
            Spacer().frame(height: 16)
            
            CommonTextField(hint: "Password", enabled: !viewState.isSending) { newValue in
                eventHandler(.PasswordChanged(value: newValue))
            }
            
            Spacer().frame(height: 16)
            
            
            LoginActionView(viewState: viewState, onForgotClicked: {
                eventHandler(.ForgotClick())
            }, onSubmitClicked: {
                eventHandler(.LoginClick())
            })
        }
        
        HStack {
            Text("Don't have account?")
                .foregroundColor(.blue)
                .opacity(0.5)
            
            Text("Create one")
                .foregroundColor(.blue)
                .fontWeight(.bold)
                .onTapGesture {
                    eventHandler(.RegistrationClick())
                }
        }
    }
}

struct LoginActionView: View{
    let viewState: LoginViewState
    let onForgotClicked: () -> Void
    let onSubmitClicked: () -> Void
    
    var body: some View{
        
        VStack {
             Spacer().frame(height: 30)
             HStack {
                 Spacer()
                 Text("Forgot Password")
                     .foregroundColor(.black)
                     .onTapGesture {
                         onForgotClicked()
                     }
                 Spacer().frame(width: 30)
             }
             Spacer().frame(height: 30)
             ActionButton(title: "Login Now", enabled: !viewState.isSending) {
                 onSubmitClicked()
             }
             .frame(height: 56)
             Spacer()
         }
    }
    
}

struct SignInUIView_Previews: PreviewProvider {
    static var previews: some View {
        SignInUIView(viewState: LoginViewState(phone_number: "bob298", password: "23456", isSending: false,passwordHidden: true),eventHandler: { event in
                
            })
        .background(Color.white)
        .background(ignoresSafeAreaEdges: [.top, .bottom])
    }
}

