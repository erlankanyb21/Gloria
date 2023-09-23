//
//  CustomTabsView.swift
//  iosApp
//
//  Created by Macbook on 18/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CustomTabsView: View {
    @Binding var index: Int

    var body: some View {
        HStack(alignment: .center){
            Spacer()
            Button(action: {
                // Действие для первой кнопки
                self.index = 0
            }) {
                VStack{
                    Image(self.index == 0 ? "home.fill" : "home")
                    Text("Главная")
                        .font(.system(size: 11))
                        .frame(width: 70)
                        .foregroundColor(self.index == 0 ? Color("purplish") : Color.gray)
                }
                
            }.foregroundColor(Color.black.opacity(self.index == 0 ? 1 : 0.2))
            
            Spacer()
            
            Button(action: {
                // Действие для первой кнопки
                self.index = 1
            }) {
                VStack{
                    Image(self.index == 1 ? "catalog.fill" : "catalog")
                    Text("Каталог")
                        .font(.system(size: 11))
                        .frame(width: 70)
                        .foregroundColor(self.index == 1 ? Color("purplish") : Color.gray)
                }
                
            }.foregroundColor(Color.black.opacity(self.index == 1 ? 1 : 0.2))
            
            Spacer()
            
            Button(action: {
                // Действие для первой кнопки
                self.index = 2
            }) {
                VStack{
                    Image(self.index == 2 ? "basket.fill" : "basket")
                    Text("Корзина")
                        .font(.system(size: 11))
                        .frame(width: 70)
                        .foregroundColor(self.index == 2 ? Color("purplish") : Color.gray)
                }
                
            }.foregroundColor(Color.black.opacity(self.index == 2 ? 1 : 0.2))
            
            Spacer()
            
            Button(action: {
                // Действие для первой кнопки
                self.index = 3
            }) {
                VStack{
                    Image(self.index == 3 ? "more.fill" : "more")
                    Text("Еще")
                        .font(.system(size: 11))
                        .frame(width: 70)
                        .foregroundColor(self.index == 3 ? Color("purplish") : Color.gray)
                }
                
            }.foregroundColor(Color.black.opacity(self.index == 3 ? 1 : 0.2))
            
            Spacer()
            
        }
    }
}

struct CustomTabsView_Previews: PreviewProvider {
    static var previews: some View {
        @State var selectedTab: Int = 0
        VStack(spacing: 0) {
            Spacer()
            VStack {
                switch selectedTab {
                case 0:
                    HomeUIView()
                case 1:
                    CatalogUIView()
                case 2:
                    BasketUIView()
                case 3:
                    MoreUIView()
                default:
                    EmptyView()
                }
            }
            Spacer()
            
            CustomTabsView(index: $selectedTab)
        }.background(Color.white)
    }
}
