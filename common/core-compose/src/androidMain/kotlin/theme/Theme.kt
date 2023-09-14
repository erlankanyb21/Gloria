package theme


import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(content: @Composable () -> Unit) {

    lightColorScheme(
        primary = Color.Transparent
    )

    CompositionLocalProvider(
        LocalColorProvider provides color,
        content = content
    )
}

object Theme {
    val colors: GloriaColor
        @Composable
        get() = LocalColorProvider.current
}