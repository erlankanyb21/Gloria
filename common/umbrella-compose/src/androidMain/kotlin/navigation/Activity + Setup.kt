package navigation

import NavigationTree
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.adeo.kviewmodel.odyssey.setupWithViewModels
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.configuration.DefaultModalConfiguration
import ru.alexgladkov.odyssey.core.configuration.DisplayType
import theme.AppTheme
import theme.Theme
import theme.color

fun ComponentActivity.setupThemedNavigation() {
    installSplashScreen()
    val rootController = RootComposeBuilder().apply { generateGraph() }.build()

    rootController.setupWithActivity(this)
    rootController.setupWithViewModels()
    setContent {
        AppTheme {
            val backgroundColor = Theme.colors.white
            rootController.backgroundColor = backgroundColor

            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT

            CompositionLocalProvider(
                LocalRootController provides rootController
            ) {

                ModalNavigator(
                    configuration = DefaultModalConfiguration(
                        color.white,
                        DisplayType.EdgeToEdge
                    )
                ) {
                    Navigator(startScreen = NavigationTree.Auth.SignIn.name)
                }
            }
        }
    }
}