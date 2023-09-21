package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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
    val purple500: Color,
    val purple200: Color,
    val purple:Color,
    val transient: Color,
    val textColor: Color,
    val royalBlue: Color,
)

val gloriaGradient = Brush.linearGradient(
    0.0f to Color(0xFF552180),
    0.5f to Color(0xFFA4128C),
    1.0f to Color(0xFFC11685),
    start = Offset(500.0f, 0.0f),
    end = Offset(0.0f, 500.0f)
)


val color = GloriaColor(
    black = Color(0xFF000000),
    white = Color(0xFFFFFFFF),
    deepPink = Color(0xFFFF1493),
    lightPink = Color(0xFFFFB8DF),
    palePink = Color(0xFFFFE7F4),
    lighterPink = Color(0xFFFFD0E9),
    mediumPink = Color(0xFFFFA1D4),
    royalBlue = Color(0xFF0051BA),
    pink = Color(0xFFFF71BE),
    deeperPink = Color(0xFFFF42A9),
    deeepPink = Color(0xFFFF1393),
    purple500 = Color(0xFF552180),
    purple200 = Color(0xFFA4128C),
    transient = Color(0x0),
    purple = Color(0xFF98158A),
    textColor = Color(0xFF98158A)
)
val LocalColorProvider =
    staticCompositionLocalOf<GloriaColor> { error("No default implementation") }