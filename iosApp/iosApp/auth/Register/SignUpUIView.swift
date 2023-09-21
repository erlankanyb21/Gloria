//
//  SignUpUIView.swift
//  iosApp
//
//  Created by Macbook on 15/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct SignUpUIView: View {
    @State private var isActive  = false
    @State private var name: String = ""
    @State private var phoneNumber: String = ""
    @State private var password: String = ""
    @State private var ConfirmPassword: String = ""
    @State private var isSecureField: Bool = true

    
    var body: some View {
        NavigationView{
            VStack(alignment: .center){
                Spacer().frame(height: 69)
                
                Text("Привет \nс создай свой аккаунт")
                    .font(.system(size: 24))
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color(red: 0.53, green: 0.09, blue: 0.53))
                
                Spacer().frame(height: 40)
                
                TextField("Введите имя", text: $name)
                    .padding(.all)
                    .overlay(RoundedRectangle(cornerRadius: 26).stroke(Color.black, lineWidth: 1)).padding([.leading,.trailing])
                    .foregroundColor(Color.black)

                
                TextField("Введите номер телефона", text: $phoneNumber)
                    .padding(.all)
                    .overlay(RoundedRectangle(cornerRadius: 26).stroke(Color.black, lineWidth: 1)).padding([.leading,.trailing])
                    .foregroundColor(Color.black)
                
                VStack(alignment: .leading) {
                    ZStack(alignment: .trailing) {
                        if isSecureField {
                            SecureField("Введите пароль", text: $password)
                        } else {
                            TextField("Введите пароль", text: $password)
                        }
                        
                        Button(action: {
                            isSecureField.toggle()
                        }) {
                            Image(systemName: isSecureField ? "eye.slash" : "eye")
                                .foregroundColor(.black)
                        }
                    }
                    .padding(.all)
                    .overlay(
                        RoundedRectangle(cornerRadius: 26)
                            .stroke(Color.black, lineWidth: 1)
                    )
                    .padding([.leading, .trailing])
                }
                
                VStack(alignment: .leading) {
                    ZStack(alignment: .trailing) {
                        if isSecureField {
                            SecureField("Подтвердите пароль", text: $ConfirmPassword)
                        } else {
                            TextField("Подтвердите пароль", text: $ConfirmPassword)
                        }
                        
                        Button(action: {
                            isSecureField.toggle()
                        }) {
                            Image(systemName: isSecureField ? "eye.slash" : "eye")
                                .foregroundColor(.black)
                        }
                    }
                    .padding(.all)
                    .overlay(
                        RoundedRectangle(cornerRadius: 26)
                            .stroke(Color.black, lineWidth: 1)
                    )
                    .padding([.leading, .trailing])
                }
                
                Spacer()
                
                CustomButtonView(
                    title: "Войти",
                    backgroundGradient: LinearGradient(gradient: Gradient(stops: [
                        Gradient.Stop(color: Color(red: 0.33, green: 0.13, blue: 0.5), location: 0.00),
                        Gradient.Stop(color: Color(red: 0.64, green: 0.07, blue: 0.55), location: 0.61),
                        Gradient.Stop(color: Color(red: 0.76, green: 0.09, blue: 0.52), location: 1.00),
                    ]),
                                                       startPoint: .topLeading,
                                                       endPoint: .bottomTrailing),
                    titleColor: Color("white"),
                    cornerRadius: 26,
                    width: 355,
                    destination: MainUIView()
                )
                
                HStack{
                    Text("Уже есть аккаунт?").font(.system(size: 14)).foregroundColor(Color("black"))
                    
                    NavigationLink(destination: SignUpUIView()) {
                        Text("Войти").font(.system(size: 14))
                    }
                    
                }
            }
            .navigationBarItems(
                trailing: NavigationLink(destination: MainUIView()) {
                    Text("Пропустить")
                        .font(Font.custom("SF Pro Text", size: 14))
                        .underline()
                        .foregroundColor(.black)
                }
            )
        }.navigationBarBackButtonHidden(true) // NavigationView
    }
}

struct SignUpUIView_Previews: PreviewProvider {
    static var previews: some View {
        SignUpUIView()
    }
}
