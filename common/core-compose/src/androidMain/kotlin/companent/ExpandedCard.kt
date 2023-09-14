package companent

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import okhttp3.internal.wait
import org.tbm.gloria.core_compose.R
import theme.gloriaGradient

@Composable
fun ExpandedCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(485.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(28.dp))
            .animateContentSize(
                tween(durationMillis = 400, easing = FastOutLinearInEasing)
            )
    ) {
        Column {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .fillMaxWidth(),
                elevation = 0.dp
            ) {
                Row(
                    Modifier
                        .background(gloriaGradient)
                        .clip(RoundedCornerShape(28.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.gloria),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .weight(2f)
                            .clip(CircleShape)
                            .size(54.dp)
                    )

                    Text(
                        modifier = Modifier.weight(4f),
                        text = "Личные данные",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )

                    Icon(
                        modifier = Modifier.weight(1f),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExpandedCardPreview() {
    ExpandedCard()
}