package cart

import cart.models.CartAction
import cart.models.CartEvent
import cart.models.CartViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import repositories.CartRepository

class CartViewModel(): BaseSharedViewModel<CartViewState, CartAction, CartEvent>(
    initialState = CartViewState(1, false)
) {

    private val cartRepository: CartRepository = Inject.instance()

    override fun obtainEvent(viewEvent: CartEvent) {
        when(viewEvent) {
            CartEvent.ClearCart -> { println("ClearCart") }
            CartEvent.RemoveItem -> { println("RemoveItem") }
            CartEvent.PlusProduct -> { println("PlusProduct") }
            CartEvent.MinusProduct -> { println("MinusProduct") }
        }
    }

    init {
        viewModelScope.launch {
            viewState = try {
                val res = cartRepository.fetchUserCart()
                viewState.copy(
                    productCount = 1,
                    isEmptyCart = false,
                    cartItems = res.cartItems)
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    productCount = 0,
                    isEmptyCart = true,
                    cartItems = emptyList())
            }
        }
    }
}