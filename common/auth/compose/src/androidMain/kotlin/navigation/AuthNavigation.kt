package navigation

import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import signin.SignIn
import signup.SignUp

fun RootComposeBuilder.authFlow(){
    screen(name = NavigationTree.Auth.SignUp.name){
        SignUp()
    }
}