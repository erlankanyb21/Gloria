package navigation

import cart.CartScreen
import catalog.CatalogScreen
import main.MainScreen
import more.MoreScreen
import more.contactAndAddress.ContactsAndAddressScreen
import more.faq.FAQScreen
import navigation.tabs.BottomConfiguration
import navigation.tabs.CartTab
import navigation.tabs.CatalogTab
import navigation.tabs.MainTab
import navigation.tabs.MoreTab
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.helpers.FlowBuilder
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
        }
        tab(CartTab()) {
            screen(name = NavigationTree.Main.Tab.name) {
                CartScreen()
            }
        }
        tab(MoreTab()) {
            moreScreens()
        }
    }
}

private fun FlowBuilder.moreScreens() {
    screen(name = NavigationTree.Main.Tab.name) {
        MoreScreen()
    }

    screen(NavigationTree.Main.FAQScreen.name) {
        FAQScreen()
    }

    screen(NavigationTree.Main.ContactsAndAddress.name) {
        ContactsAndAddressScreen()
    }
}