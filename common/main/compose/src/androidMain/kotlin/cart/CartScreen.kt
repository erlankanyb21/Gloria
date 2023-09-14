package cart

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cart.cart_views.CartListItem
import cart.cart_views.CartTopAppBar
import cart.cart_views.EmptyCartScreen
import cart.models.CartAction
import cart.models.CartEvent
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import org.tbm.gloria.core_compose.R

@Composable
fun CartScreen() {

    StoredViewModel(factory = { CartViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()

        Scaffold(
            topBar = { CartTopAppBar(title = stringResource(id = R.string.cart), viewState) }
        ) { padding ->
            if (viewState.value.isNotEmptyCart) {
                LazyColumn(
                    modifier = Modifier.padding(padding)
                ) {
                    viewState.value.cartItems.forEachIndexed { index, item ->
                        item {
                            CartListItem(item, viewState) {
                                viewModel.obtainEvent(CartEvent.DecrementProductCount(index))
                            }
                        }
                    }
                }
            } else {
                EmptyCartScreen(padding) { event ->
                    viewModel.obtainEvent(event)
                }
            }
        }
        when (viewAction.value) {
            CartAction.OpenCatalog -> {
                Toast.makeText(LocalContext.current, "Click", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
}