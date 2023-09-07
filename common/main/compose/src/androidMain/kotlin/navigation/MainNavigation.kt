package navigation

import MainScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.mainFlow(){
    flow(name = NavigationTree.Main.MainScreen.name) {
        screen(name = NavigationTree.Main.MainScreen.name) {
            MainScreen()
        }
    }
}