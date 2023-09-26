//
//  MainUIView.swift
//  iosApp
//
//  Created by Macbook on 15/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MainUIView: View {
    @AppStorage("token") var accessToken: String?
    @State private var selectedTab: Int = 0
    @State private var navigateToDetail = true
    var body: some View {
        NavigationView{
            VStack(spacing: 0){
                Spacer()
                VStack{
                    switch selectedTab{
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
        }.navigationBarBackButtonHidden(true)
    }
}

struct MainUIView_Previews: PreviewProvider {
    static var previews: some View {
        MainUIView()
    }
}
