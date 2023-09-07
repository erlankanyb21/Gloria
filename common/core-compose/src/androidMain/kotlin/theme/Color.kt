package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class GloriaColor(
    val black: Color,
    val white: Color,
    val deepPink: Color,
    val lightPink: Color,
    val palePink: Color,
    val lighterPink: Color,
    val mediumPink: Color,
    val pink: Color,
    val deeperPink: Color,
    val deeepPink: Color,


    )

val color = GloriaColor(
    black = Color(0xFF000000),
    white = Color(0xFFFFFFFF),
    deepPink = Color(0xFFFF1493),
    lightPink = Color(0xFFFFB8DF),
    palePink = Color(0xFFFFE7F4),
    lighterPink = Color(0xFFFFD0E9),
    mediumPink = Color(0xFFFFA1D4),
    pink = Color(0xFFFF71BE),
    deeperPink = Color(0xFFFF42A9),
    deeepPink = Color(0xFFFF1393)
)
val LocalColorProvider = staticCompositionLocalOf<GloriaColor> { error("No default implementation") }

