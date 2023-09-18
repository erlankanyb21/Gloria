package navigation

import cart.cart_screen.CartScreen
import catalog.CatalogScreen
import main.MainScreen
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
            screen(name = NavigationTree.Main.Main.name) {
                MainScreen()
            }
        }
        tab(CatalogTab()) {
            screen(name = NavigationTree.Main.Catalog.name) {
                CatalogScreen()
            }
        }
        tab(CartTab()) {
            screen(name = NavigationTree.Main.Cart.name) {
                CartScreen()
            }
        }
        tab(MoreTab()) {
            screen(name = NavigationTree.Main.More.name) {
                MoreScreen()
            }
        }
    }
}