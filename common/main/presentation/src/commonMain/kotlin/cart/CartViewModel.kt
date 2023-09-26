package cart

import cart.models.CartAction
import cart.models.CartEvent
import cart.models.CartViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import repositories.cart.CartRepository

class CartViewModel : BaseSharedViewModel<CartViewState, CartAction, CartEvent>(
    initialState = CartViewState()
) {

    private val cartRepository: CartRepository = Inject.instance()

    override fun obtainEvent(viewEvent: CartEvent) {
        when (viewEvent) {
            is CartEvent.ClearCart -> { clearCart() }
            is CartEvent.RemoveItem -> { removeItem(viewEvent.productId) }
            is CartEvent.IncrementProductCount -> { incrementProductCount(viewEvent.value) }
            is CartEvent.DecrementProductCount -> { decrementProductCount(viewEvent.value) }
            is CartEvent.OpenCatalogClick -> { openCatalog() }
            is CartEvent.OnBackClick -> { onBackClick() }
        }
    }

    private fun onBackClick() {
        viewAction = CartAction.OnBackClick
    }

    private fun removeItem(productId: Int) {
        viewModelScope.launch {
            cartRepository.deleteCartItem(productId)
            fetchUserCart()
        }
    }

    private fun openCatalog() {
        viewAction = CartAction.OpenCatalog
    }

    private fun decrementProductCount(position: Int) {

    }

    private fun incrementProductCount(position: Int) {
        viewState = try {
            val cartItems = viewState.cartItems.toMutableList()
            var count = cartItems[position].quantity
            val item = cartItems[position].copy(
                quantity = ++count
            )
            cartItems[position] = item
            viewState.copy(
                cartItems = cartItems
            )
        } catch (e: Exception) {
            viewState
        }
    }

    fun fetchUserCart() {
        viewModelScope.launch {
            viewState = try {
                val res = cartRepository.fetchUserCart()
                viewState.copy(
                    loading = false,
                    cartItems = res
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    loading = false,
                    cartItems = emptyList()
                )
            }
        }
    }

    private fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
            fetchUserCart()
        }
    }
}