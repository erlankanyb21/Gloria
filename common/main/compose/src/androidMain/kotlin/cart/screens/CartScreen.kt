package cart.screens

import NavigationTree
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cart.cart.CartViewModel
import cart.cart.models.CartAction
import cart.cart.models.CartEvent
import cart.cart_views.CartListItem
import cart.cart_views.CartTopAppBar
import cart.cart_views.EmptyCartScreen
import cart.cart_views.LoadingScreen
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.GradientButton
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreen() {
    val rootController = LocalRootController.current
    val modalController = rootController.findModalController()
    val isVisibleButton = rememberSaveable { mutableStateOf(true) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -20) {
                    isVisibleButton.value = false
                }
                if (available.y > 20) {
                    isVisibleButton.value = true
                }
                return Offset.Zero
            }
        }
    }

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
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = paddingValues.calculateTopPadding())
                                .nestedScroll(nestedScrollConnection),
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

                        AnimatedVisibility(
                            modifier = Modifier.align(Alignment.Center),
                            visible = viewState.value.progress
                        ) {
                            CircularProgressIndicator(color = color.purple200)
                        }

                        AnimatedVisibility(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            visible = isVisibleButton.value,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            GradientButton(
                                text = stringResource(id = R.string.place_order),
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .padding(bottom = 30.dp)
                            ) {
                                viewModel.obtainEvent(CartEvent.OpenPlaceOrder)
                            }
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
            is CartAction.OpenCatalog -> {
                rootController.findHostController()?.switchTab(1)
            }
            is CartAction.OnBackClick -> {
                rootController.popBackStack()
            }
            is CartAction.OpenPlaceOrder -> {
                rootController.present(NavigationTree.Main.PlaceOrder.name)
            }
            else -> {}
        }
    }
}