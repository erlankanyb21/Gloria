package catalog.catalog_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import models.catalog.ResultsItem
import org.tbm.gloria.core_compose.R
import theme.gloriaGradient

@Composable
fun CardProductView(
    item: ResultsItem,
) {
    var isFavorite = remember {
        mutableStateOf(false)
    }
    val image: String? = item.productImages?.get(0)?.image

    val imageView = if (image != null && !image.contains("https")) {
        image.replace("http", "https")
    } else {
        image
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 14.dp),
        ) {
            AsyncImage(
                model = imageView,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(135.dp)
                    .height(125.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(.7f),
                    text = item.name, style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 12.27.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Black
                    )
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(46.dp)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(
                            0xFFE61A93
                        )
                    ),
                    contentPadding = PaddingValues(horizontal = 10.5.dp),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text(
                        text = "ХИТ!", style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 21.05.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = item.price,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 12.27.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF552180),
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .width(98.dp)
                        .height(30.dp),
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .background(gloriaGradient)
                            .width(98.dp)
                            .height(30.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "В корзину", style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
                Image(
                    modifier = Modifier.clickable {
                        isFavorite.value = !isFavorite.value
                    },
                    painter = if (isFavorite.value) painterResource(id = org.tbm.gloria.main.compose.R.drawable.ic_infavorite) else painterResource(
                        id = R.drawable.ic_favorite
                    ),
                    contentDescription = null
                )
            }
        }
    }
}