package navigation

import cart.CartScreen
import catalog.CatalogScreen
import home.HomeScreen
import home.StoriesDetailsScreen
import more.MoreScreen
import more.faq.FAQScreen
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
        name = NavigationThree.Main.MainScreen.name,
        tabsNavModel = BottomConfiguration(),
    ) {
        tab(MainTab()) {
            screen(name = NavigationThree.Main.Tab.name) {
                HomeScreen()
            }

            screen(NavigationThree.Main.FAQScreen.name) {
                FAQScreen()
            }

            screen(NavigationThree.Main.StoriesDetailsScreen.name) {
                StoriesDetailsScreen()
            }
        }
        tab(CatalogTab()) {
            screen(name = NavigationThree.Main.Tab.name) {
                CatalogScreen()
            }
        }
        tab(CartTab()) {
            screen(name = NavigationThree.Main.Tab.name) {
                CartScreen()
            }
        }
        tab(MoreTab()) {
            screen(name = NavigationThree.Main.Tab.name) {
                MoreScreen()
            }

            screen(NavigationThree.Main.FAQScreen.name) {
                FAQScreen()
            }
        }
    }
}