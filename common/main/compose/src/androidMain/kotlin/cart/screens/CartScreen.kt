package cart.screens

import NavigationTree
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.cart.CartViewModel
import cart.cart.models.CartAction
import cart.cart.models.CartEvent
import cart.cart_views.CartListItem
import cart.cart_views.CartTopAppBar
import cart.cart_views.EmptyCartScreen
import cart.cart_views.LoadingScreen
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color
import theme.gloriaGradient

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreen() {
    val rootController = LocalRootController.current
    val modalController = rootController.findModalController()

    StoredViewModel(factory = { CartViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.fetchUserCart()
        }

        Scaffold(
            topBar = {
                CartTopAppBar(
                    title = stringResource(id = R.string.cart),
                    viewState = viewState,
                    modalController = modalController
                ) {
                    viewModel.obtainEvent(it)
                }
            }
        ) { paddingValues ->
            if (viewState.value.loading) {
                LoadingScreen(paddingValues)
            } else {
                if (viewState.value.cartItems.isNotEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = paddingValues.calculateTopPadding()),
                            contentPadding = PaddingValues(
                                start = 20.dp, top = 20.dp, end = 20.dp, bottom = 80.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            itemsIndexed(
                                items = viewState.value.cartItems,
                                key = { _, item -> item.id }
                            ) { index, item ->
                                CartListItem(
                                    Modifier.animateItemPlacement(),
                                    item, index, modalController
                                ) { event ->
                                    viewModel.obtainEvent(event)
                                }
                            }
                        }
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .clip(RoundedCornerShape(40.dp))
                                .background(gloriaGradient),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            onClick = {
                                viewModel.obtainEvent(CartEvent.OpenPlaceOrder)
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.place_order),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = color.white
                                )
                            )
                        }
                    }
                } else {
                    EmptyCartScreen(paddingValues) { event ->
                        viewModel.obtainEvent(event)
                    }
                }
            }
        }

        when (viewAction.value) {
            CartAction.OpenCatalog -> {
                rootController.findHostController()?.switchTab(1)
            }
            CartAction.OnBackClick -> {
                rootController.popBackStack()
            }
            CartAction.OpenPlaceOrder -> {
                rootController.present(NavigationTree.Main.PlaceOrder.name)
            }
            else -> {}
        }
    }
}