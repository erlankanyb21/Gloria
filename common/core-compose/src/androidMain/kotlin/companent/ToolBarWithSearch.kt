package companent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.tbm.gloria.core_compose.R
import theme.gloriaGradient


@Composable
fun ToolBarWithSearch(
    title: String,
    painter: Painter? = null,
    onClick: Unit
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .background(gloriaGradient)
            .statusBarsPadding()
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.
            padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (painter != null) {
                Image(
                    modifier = Modifier
                        .clickable {onClick},
                    painter = painter,
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White,
                ),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            SearchBar(text = "", readOnly = false, onValueChange = {}, onSearch = {})
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun ToolBarContent() {
    ToolBarWithSearch(title = "Каталог",
        painterResource(id = R.drawable.ic_back_arrow),
        onClick = Unit)
}