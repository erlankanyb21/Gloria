package navigation

import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import signin.SignIn

fun RootComposeBuilder.authFlow(){
    flow(name = NavigationTree.Auth.AuthFlow.name){
       screen(name = NavigationTree.Auth.SignIn.name){
           SignIn()
       }
    }
}