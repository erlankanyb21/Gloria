package cart.screens

import NavigationTree
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.successful_order.SuccessfulOrderViewModel
import cart.successful_order.models.SuccessfulOrderAction
import cart.successful_order.models.SuccessfulOrderEvent
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.BackIcon
import components.GradientButton
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@Composable
fun SuccessfulOrderScreen() {

    StoredViewModel(factory = { SuccessfulOrderViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()

        val rootController = LocalRootController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BackIcon(
                modifier = Modifier
                    .padding(top = 16.dp, start = 20.dp)
                    .size(20.dp),
                iconTint = color.black
            ) {
                viewModel.obtainEvent(SuccessfulOrderEvent.OnBackClick)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_successful),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Ваш заказ успешно оформлен!",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = color.black
                    )
                )
            }
            GradientButton(
                text = "Перейти на главную",
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp)
            ) {
                viewModel.obtainEvent(SuccessfulOrderEvent.OpenMainScreenClick)
            }
        }
        when(viewAction.value) {
            SuccessfulOrderAction.OnBackClick -> {
                rootController.popBackStack()
            }
            SuccessfulOrderAction.OpenMainScreen -> {
                rootController.findHostController()?.switchTab(0)
                rootController.backToScreen(NavigationTree.Main.Cart.name)
            }
            else -> {}
        }
    }
}