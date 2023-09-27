package cart.cart_views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.place_order.models.PlaceOrderEvent
import cart.place_order.models.PlaceOrderViewState
import theme.color
import theme.gloriaGradient

@Composable
fun SelectFilialView(
    modifier: Modifier = Modifier,
    viewState: State<PlaceOrderViewState>,
    handleEvent: (PlaceOrderEvent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val rotateCardIcon by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(28.dp),
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium
                )
            ),
        border = BorderStroke(1.dp, gloriaGradient),
    ) {
        Column {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(28.dp))
                    .fillMaxWidth()
                    .size(54.dp)
                    .border(1.dp, gloriaGradient, CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { expanded = !expanded }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    text = viewState.value.selectedFilial,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = color.black,
                    )
                )

                Icon(
                    modifier = modifier
                        .align(Alignment.CenterEnd)
                        .rotate(rotateCardIcon)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { expanded = !expanded },
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    tint = color.purple200
                )
            }

            if (expanded) {
                Column(
                    modifier = modifier
                        .padding(bottom = 16.dp)
                        .background(Color.White)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column {
                        viewState.value.filialList.forEach { item ->
                            Text(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp)
                                    .padding(top = 12.dp)
                                    .clickable {
                                        handleEvent(PlaceOrderEvent.SelectFilial(item.nameAddress))
                                        expanded = false
                                    },
                                text = item.nameAddress,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = color.black,
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}