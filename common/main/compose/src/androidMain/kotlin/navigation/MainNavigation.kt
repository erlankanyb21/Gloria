package navigation

import cart.CartScreen
import catalog.CatalogDetailScreen
import catalog.CatalogScreen
import catalog.CatalogViewModel
import catalog.SubcatalogScreen
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import main.MainScreen
import models.catalog.CatalogItem
import more.MoreScreen
import navigation.tabs.BottomConfiguration
import navigation.tabs.CartTab
import navigation.tabs.CatalogTab
import navigation.tabs.MainTab
import navigation.tabs.MoreTab
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.mainFlow() {
    bottomNavigation(
        name = NavigationTree.Main.MainScreen.name,
        tabsNavModel = BottomConfiguration(),
    ) {
        tab(MainTab()) {
            screen(name = NavigationTree.Main.Tab.name) {
                MainScreen()
            }
        }
        tab(CatalogTab()) {
            screen(name = NavigationTree.Main.Tab.name) {
                CatalogScreen()
            }
            screen(name = NavigationTree.Main.SubcatalogScreen.name){
                    SubcatalogScreen(slug = it as String)
            }
            screen(name = NavigationTree.Main.CatalogDetailScreen.name){
                CatalogDetailScreen()
            }
        }
        tab(CartTab()) {
            screen(name = NavigationTree.Main.Tab.name) {
                CartScreen()
            }
        }
        tab(MoreTab()) {
            screen(name = NavigationTree.Main.Tab.name) {
                MoreScreen()
            }
        }
    }
}