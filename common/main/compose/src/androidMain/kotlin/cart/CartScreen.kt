package cart

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.models.CartViewState
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import companent.ToolBar
import theme.color

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CartScreen() {

    StoredViewModel(factory = { CartViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()
        
        Scaffold(
            topBar = { ToolBar(title = "Корзина") }
        ) { 
            LazyColumn {
                viewState.value.cartItems.forEach {
                    item {
                        CartItem(viewState)
                    }
                }
            }
        }
    }
}

@Composable
fun CartItem(viewState: State<CartViewState>) {
    Box(
        modifier = Modifier
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
                painter = painterResource(id = androidx.core.R.drawable.notify_panel_notification_icon_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Букет роз",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = color.black
                    )
                )

                Text(
                    text = "описание товара",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = color.black
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
                                id = org.tbm.gloria.core_compose.R.drawable.ic_minus),
                            contentDescription = null)
                    }

                    Text(
                        text = "1",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    IconButton(
                        onClick = {  }
                    ) {
                        Image(
                            painter = painterResource(
                                id = org.tbm.gloria.core_compose.R.drawable.ic_plus),
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
                    id = org.tbm.gloria.core_compose.R.drawable.ic_remove),
                contentDescription = null,
            )
        }
    }
}