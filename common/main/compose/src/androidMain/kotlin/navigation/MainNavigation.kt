package navigation

import NavigationTree
import cart.screens.CartScreen
import cart.screens.PlaceOrderScreen
import cart.screens.SuccessfulOrderScreen
import catalog.CatalogScreen
import home.HomeScreen
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
            screen(name = NavigationTree.Main.MainScreen.name) {
                HomeScreen()
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
            screen(name = NavigationTree.Main.PlaceOrder.name) {
                PlaceOrderScreen()
            }
            screen(name = NavigationTree.Main.SuccessfulOrder.name) {
                SuccessfulOrderScreen()
            }
        }
        tab(MoreTab()) {
            screen(name = NavigationTree.Main.More.name) {
                MoreScreen()
            }
        }
    }
}