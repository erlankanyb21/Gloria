package cart.cart_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.cart.models.CartEvent
import components.GradientButton
import org.tbm.gloria.core_compose.R
import theme.color

@Composable
fun EmptyCartScreen(
    padding: PaddingValues,
    eventHandler: (CartEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = padding.calculateTopPadding())
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(1.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(
                    id = R.drawable.ic_cart
                ),
                tint = color.purple200,
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.your_cart_is_empty),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = color.black
                )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(id = R.string.empty_cart_desc),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = color.black
                ),
                textAlign = TextAlign.Center
            )
        }
        GradientButton(
            text = stringResource(id = R.string.to_catalog),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp)
        ) {
            eventHandler.invoke(CartEvent.OpenCatalogClick)
        }
    }
}