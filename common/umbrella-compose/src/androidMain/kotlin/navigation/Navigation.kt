package navigation

import NavigationTree
import catalog.CatalogDetailScreen
import catalog.SubcatalogScreen
import home.StoriesDetailsScreen
import more.contactAndAddress.ContactsAndAddressScreen
import more.faq.FAQScreen
import more.favorite.FavoriteScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.generateGraph() {
    flow(name = NavigationTree.Main.MainFlow.name) {
        screen(name = NavigationTree.Main.StoriesDetails.name) {
            StoriesDetailsScreen(id = it as Int)
        }
        screen(name = NavigationTree.Main.ContactsAndAddress.name) {
            ContactsAndAddressScreen()
        }
        screen(name = NavigationTree.Main.FAQ.name) {
            FAQScreen()
        }
        screen(name = NavigationTree.Main.Favorite.name) {
            FavoriteScreen()
        }
        screen(name = NavigationTree.Main.Subcatalog.name) {
            SubcatalogScreen(slug = it as String)
        }
        screen(name = NavigationTree.Main.CatalogDetailScreen.name) {
            CatalogDetailScreen()
        }
    }
    mainFlow()
    authFlow()
}