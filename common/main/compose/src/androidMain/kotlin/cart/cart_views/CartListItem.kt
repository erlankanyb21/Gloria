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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.cart.models.CartEvent
import coil.compose.AsyncImage
import extensions.textBrush
import models.cart.CartItems
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.controllers.ModalController
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.AlertConfiguration
import theme.color
import theme.gloriaGradient

@Composable
fun CartListItem(
    modifier: Modifier = Modifier,
    item: CartItems,
    index: Int,
    modalController: ModalController,
    eventHandler: (CartEvent) -> Unit
) {
    val productImage = item.productImages[0].image
    val image = if (!productImage.contains("https")) {
        productImage.replace("http", "https")
    } else {
        productImage
    }
    Box(
        modifier = modifier
            .border(1.dp, color.purple200, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.TopEnd
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = image,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.name ?: stringResource(id = R.string.unknown),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = color.black
                    )
                )

                Text(
                    text = item.description ?: stringResource(id = R.string.unknown),
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
                        onClick = {
                            eventHandler(CartEvent.DecrementProductCount(index))
                        }
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_minus
                            ),
                            contentDescription = null
                        )
                    }

                    Text(
                        modifier = Modifier.textBrush(gloriaGradient),
                        text = item.quantity.toString(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    IconButton(
                        onClick = {
                            eventHandler(CartEvent.IncrementProductCount(index))
                        }
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_plus
                            ),
                            contentDescription = null
                        )
                    }
                }
            }
        }
        IconButton(
            onClick = {
                val alertConfiguration = AlertConfiguration(
                    maxHeight = 0.3f,
                    maxWidth = 0.8f,
                    cornerRadius = 8
                )
                modalController.present(alertConfiguration) { key ->
                    AlertDialogScreen(
                        text = stringResource(id = R.string.un_cart),
                        positiveButtonText = stringResource(id = R.string.delete),
                        onPositiveClick = {
                            eventHandler.invoke(CartEvent.RemoveItem(item.id))
                        }
                    ) {
                        modalController.popBackStack(key)
                    }
                }
            }
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_remove
                ),
                contentDescription = null,
            )
        }
    }
}