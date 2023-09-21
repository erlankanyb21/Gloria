//
//  RestorePasswordUIView.swift
//  iosApp
//
//  Created by Macbook on 15/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RestorePasswordUIView: View {
    var body: some View {
        NavigationView{
            VStack(alignment: .center){
                Text("Подтвердить номер \nс телефона")
                    .font(.system(size: 24))
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color(red: 0.53, green: 0.09, blue: 0.53))
                Spacer().frame(height: 40)
                
                Text("Текстовое сообщение с кодом проверки  \nс отправлено на +996 ххх ххх 666")
                    .font(.system(size: 14))
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color.black)
                Spacer().frame(height: 40)
                
                Text("Введите код:").font(.system(size: 14))
                                
                Button(action:{
                    print("reset sms")
                }){
                    Text("Отправить код повторно").font(.system(size: 12)).foregroundColor(Color.black)
                }
                
                Spacer()
                
                CustomButtonView(
                    title: "Подтвердить",
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
                
                Spacer().frame(height: 40)

            }.navigationBarItems(leading: NavigationLink(destination: SignUpUIView()){
                Image("back")
            })
            .navigationBarBackButtonHidden(true)
        }
    }
}

struct RestorePasswordUIView_Previews: PreviewProvider {
    static var previews: some View {
        RestorePasswordUIView()
    }
}


