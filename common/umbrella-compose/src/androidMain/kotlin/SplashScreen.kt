import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import kotlinx.coroutines.delay
import navigation.NavigationTree
import org.tbm.gloria.umbrella_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun SplashScreen(){
    val rootController = LocalRootController.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.gloria),
            contentDescription = null
        )
        LaunchedEffect(key1 = true) {
            delay(3000L)

            rootController.present(NavigationTree.Auth.SignUp.name)
        }
    }
}