package cart

import cart.models.CartAction
import cart.models.CartEvent
import cart.models.CartViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import repositories.CartRepository

class CartViewModel : BaseSharedViewModel<CartViewState, CartAction, CartEvent>(
    initialState = CartViewState()
) {

    private val cartRepository: CartRepository = Inject.instance()

    override fun obtainEvent(viewEvent: CartEvent) {
        when(viewEvent) {
            is CartEvent.ClearCart -> {

            }
            is CartEvent.RemoveItem -> {

            }
            is CartEvent.IncrementProductCount -> {
                incrementProductCount(viewEvent.value)
            }
            is CartEvent.DecrementProductCount -> {
                decrementProductCount(viewEvent.value)
            }
            is CartEvent.OpenCatalogClick -> {
                openCatalog()
            }
        }
    }

    private fun openCatalog() {
        viewAction = CartAction.OpenCatalog
    }

    private fun decrementProductCount(position: Int) {
        viewState.cartItems[position].productCount.plus(1)
    }

    private fun incrementProductCount(position: Int) {

    }

    init {
    }

    private fun fetchUserCart() {
        viewModelScope.launch {
            viewState = try {
                val res = cartRepository.fetchUserCart()
                viewState.copy(
                    isNotEmptyCart = res.cartItems.isNotEmpty(),
                    cartItems = res.cartItems
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    cartItems = emptyList()
                )
            }
        }
    }
}