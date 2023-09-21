//
//  PasswordTextField.swift
//  iosApp
//
//  Created by Macbook on 19/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//
//
import SwiftUI
import SwiftUI

struct PasswordTextField: View {
    @State private var password: String = ""
    @State private var isPasswordVisible: Bool = false
    
    private let hint: String
    private let enabled: Bool
    private let onValueChanged: (String) -> Void
    
    init(hint: String, enabled: Bool = true, onValueChanged: @escaping (String) -> Void) {
        self.hint = hint
        self.enabled = enabled
        self.onValueChanged = onValueChanged
    }
    
    var body: some View {
        VStack {
            HStack {
                if isPasswordVisible {
                    TextField(hint, text: $password)
                } else {
                    SecureField(hint, text: $password)
                }
                
                Button(action: {
                    isPasswordVisible.toggle()
                }) {
                    Image(systemName: isPasswordVisible ? "eye.slash" : "eye")
                        .foregroundColor(.black)
                }
            }
            .padding()
            .background(RoundedRectangle(cornerRadius: 26).stroke(Color.black, lineWidth: 1))
            
        }
        .padding()
        .onReceive([self.password].publisher.first()) { value in
            onValueChanged(value)
        }
    }
}

struct PasswordTextField_Previews: PreviewProvider {
    static var previews: some View {
        PasswordTextField(hint: "Пароль", onValueChanged: { newValue in
            // Обработка изменения значения пароля
            print(newValue)
        })
    }
}
