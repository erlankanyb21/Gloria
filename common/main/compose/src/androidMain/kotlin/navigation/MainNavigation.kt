package navigation

import NavigationTree
import cart.screens.CartScreen
import cart.screens.PlaceOrderScreen
import cart.screens.SuccessfulOrderScreen
import catalog.CatalogDetailScreen
import catalog.CatalogScreen
import catalog.SubcatalogScreen
import home.HomeScreen
import home.StoriesDetailsScreen
import kotlinx.serialization.builtins.serializer
import more.MoreScreen
import more.contactAndAddress.ContactsAndAddressScreen
import more.faq.FAQScreen
import more.favorite.FavoriteScreen
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

            screen(name = NavigationTree.Main.StoriesDetails.name) {
                StoriesDetailsScreen(id = it as Int)
            }

            screen(name = NavigationTree.Main.FAQ.name) {
                FAQScreen()
            }

            screen(name = NavigationTree.Main.ContactsAndAddress.name) {
                ContactsAndAddressScreen()
            }
        }
        tab(CatalogTab()) {
            screen(name = NavigationTree.Main.Catalog.name) {
                CatalogScreen()
            }
            screen(name = NavigationTree.Main.Subcatalog.name) {
                SubcatalogScreen(slug = it as String)
            }
            screen(name = NavigationTree.Main.CatalogDetailScreen.name) {
                CatalogDetailScreen()
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

            screen(NavigationTree.Main.FAQ.name) {
                FAQScreen()
            }

            screen(NavigationTree.Main.ContactsAndAddress.name) {
                ContactsAndAddressScreen()
            }

            screen(NavigationTree.Main.Favorite.name) {
                FavoriteScreen()
            }
        }
    }
}