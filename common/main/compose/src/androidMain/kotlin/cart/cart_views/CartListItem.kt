package cart.cart_views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.models.CartViewState
import extensions.textBrush
import models.cart.CartItems
import org.tbm.gloria.core_compose.R
import theme.color
import theme.gloriaGradient

@Composable
fun CartListItem(item: CartItems, viewState: State<CartViewState>, eventHandler: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 12.dp)
            .border(1.dp, color.purple200, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.TopEnd
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = coil.base.R.drawable.notify_panel_notification_icon_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.name ?: stringResource(id = R.string.cart),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = color.black
                    )
                )

                Text(
                    text = item.description ?: stringResource(id = R.string.cart),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = color.black,
                    )
                )

                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .border(
                            border = BorderStroke(1.dp, color.purple200),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { }
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_minus),
                            contentDescription = null)
                    }

                    Text(
                        modifier = Modifier.textBrush(gloriaGradient),
                        text = item.productCount.toString(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    IconButton(
                        onClick = {
                            eventHandler()
                        }
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_plus),
                            contentDescription = null)
                    }
                }
            }
        }
        IconButton(
            onClick = {  }
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_remove),
                contentDescription = null,
            )
        }
    }
}