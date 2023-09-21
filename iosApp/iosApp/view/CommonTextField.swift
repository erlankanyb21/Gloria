//
//  CommonTextField.swift
//  iosApp
//
//  Created by Macbook on 18/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CommonTextField: View {
    @State private var value: String = ""
    private let hint: String
    private let enabled: Bool
    private let onValueChanged: (String) -> Void
    
    init(hint: String, enabled: Bool = true,
         onValueChanged: @escaping (String) -> Void) {
        self.hint = hint
        self.enabled = enabled
        self.onValueChanged = onValueChanged
    }
    
    var body: some View {
        ZStack(alignment: .leading) {
            RoundedRectangle(cornerRadius: 26)
                .foregroundColor(Color.white)
                .overlay(
                    RoundedRectangle(cornerRadius: 26)
                        .stroke(Color.black, lineWidth: 1)
                )
            
            if (value.isEmpty) {
                Text(hint)
                    .foregroundColor(Color.black)
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                    .font(.system(size: 16))
            }
                TextField("", text: $value)
                    .foregroundColor(.white)
                    .font(.system(size: 16))
                    .autocapitalization(.none)
                    .disabled(!enabled)
                    .disableAutocorrection(true)
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                    .onChange(of: value) { newValue in
                        onValueChanged(newValue)
                    }
            
        }
        .frame(height: 56)
        .padding(EdgeInsets(top: 0, leading: 24, bottom: 0, trailing: 24))
    }
}


struct CommonTextField_Previews: PreviewProvider {
    static var previews: some View {
        CommonTextField(hint: "Your Email", enabled: true, onValueChanged: { newValue in
            print(newValue)
        })
    }
}
