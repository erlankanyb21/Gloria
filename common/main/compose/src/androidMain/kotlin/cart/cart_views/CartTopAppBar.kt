package cart.cart_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.models.CartViewState
import extensions.bottomBorder
import org.tbm.gloria.core_compose.R
import theme.color
import theme.gloriaGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopAppBar(
    title: String,
    viewState: State<CartViewState>
) {

    CenterAlignedTopAppBar(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .background(gloriaGradient)
            .statusBarsPadding()
            .fillMaxWidth(),
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = color.white,
                )
            )
        },
        navigationIcon = {
            if (viewState.value.isNotEmptyCart) {
                IconButton(
                    onClick = { },
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_back_arrow,
                        ),
                        contentDescription = null,
                        tint = color.white
                    )
                }
            }
        },
        actions = {
            if (viewState.value.isNotEmptyCart)
                Text(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .bottomBorder(1.dp, color.white)
                        .clickable {

                        },
                    text = "Очистить",
                    color = color.white
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
    )
}