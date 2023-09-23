//
//  HybridTextFieldUsageView.swift
//  iosApp
//
//  Created by Macbook on 20/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HybridTextFieldUsageView: View {
    @Binding var text: String
    @State var isSecure: Bool = true
    var titleKey: String
    var body: some View {
        HStack{
            Group{
                if isSecure{
                    SecureField(titleKey, text: $text)
                    
                }else{
                    TextField(titleKey, text: $text)
                }
            }.textFieldStyle(.roundedBorder)
                .animation(.easeInOut(duration: 0.2), value: isSecure)
            //Add any common modifiers here so they dont have to be repeated for each Field
            Button(action: {
                isSecure.toggle()
            }, label: {
                Image(systemName: !isSecure ? "eye.slash" : "eye" )
            })
        }//Add any modifiers shared by the Button and the Fields here
    }
}

struct HybridTextFieldUsageView_Previews: PreviewProvider {
    static var previews: some View {
        HybridTextFieldUsageView(text: .constant(""), titleKey: "")
    }
}
