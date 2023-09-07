package theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(content: @Composable () -> Unit) {
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