package theme

import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun AppTheme(content: @Composable () -> Unit) {

    lightColors(
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