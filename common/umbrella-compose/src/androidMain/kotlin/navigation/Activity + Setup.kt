package navigation

import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.adeo.kviewmodel.odyssey.setupWithViewModels
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import theme.AppTheme
import theme.Theme

fun ComponentActivity.setupThemedNavigation() {
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
                ModalNavigator {
                    Navigator(startScreen = NavigationTree.SplashScreen.SplashScreen.name)
                }
            }
        }
    }
}