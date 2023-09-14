package catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.color
import theme.gloriaGradient

@Preview
@Composable
fun CatalogScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(gloriaGradient),
        contentAlignment = Alignment.TopStart,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Каталог",
            color = Color.White,

        )
    }
}