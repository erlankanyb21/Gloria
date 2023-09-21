//
//  LoginTextField.swift
//  iosApp
//
//  Created by Macbook on 19/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LoginTextField: View {
    @State private var phone_number: String = ""    
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
                TextField(hint, text: $phone_number)
            }
            .padding()
            .background(RoundedRectangle(cornerRadius: 26).stroke(Color.black, lineWidth: 1))
            
        }.padding().onReceive([self.phone_number].publisher.first()){ value in
            onValueChanged(value)
        }
    }
}

struct LoginTextField_Previews: PreviewProvider {
    static var previews: some View {
        LoginTextField(hint: "Your Email", enabled: true, onValueChanged: { newValue in
            print(newValue)
        })
    }
}
