package companent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.gloriaGradient


@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        shape = CircleShape,
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gloriaGradient)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text, color = Color.White)
        }
    }
}

@Preview
@Composable
private fun Content() {
    Column {
        GradientButton(
            text = "Оформить заказ"
        )
    }
}