package theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    pink = Color(0xFFFF71BE),
    deeperPink = Color(0xFFFF42A9),
    deeepPink = Color(0xFFFF1393)
)
val LocalColorProvider =
    staticCompositionLocalOf<GloriaColor> { error("No default implementation") }