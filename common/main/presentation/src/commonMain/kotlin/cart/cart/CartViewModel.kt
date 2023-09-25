package cart.cart

import cart.cart.models.CartAction
import cart.cart.models.CartEvent
import cart.cart.models.CartViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import models.cart.SetProductQuantityModel
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
            is CartEvent.OpenPlaceOrder -> { openPlaceOrder() }
        }
    }

    private fun openPlaceOrder() {
        viewAction = CartAction.OpenPlaceOrder
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
        viewModelScope.launch {
            viewState = try {
                val cartItems = viewState.cartItems.toMutableList()
                val itemId = cartItems[position].id
                val count = --cartItems[position].quantity
                val product = cartItems[position].product
                val user = cartItems[position].user

                if (count > 0) {
                    val item = cartRepository.setProductQuantity(
                        itemId,
                        SetProductQuantityModel(count,product,user))

                    cartItems[position] = item
                    viewState.copy(
                        cartItems = cartItems
                    )
                } else {
                    cartItems[position] = cartItems[position].copy(quantity = 1)
                    viewState.copy(cartItems = cartItems)
                }
            } catch (e: Exception) {
                viewState
            }
        }
    }

    private fun incrementProductCount(position: Int) {
        viewModelScope.launch {
            viewState = try {
                val cartItems = viewState.cartItems.toMutableList()
                val itemId = cartItems[position].id
                val count = ++cartItems[position].quantity
                val product = cartItems[position].product
                val user = cartItems[position].user

                val item = cartRepository.setProductQuantity(
                    itemId,
                    SetProductQuantityModel(count,product,user))

                cartItems[position] = item
                viewState.copy(
                    cartItems = cartItems
                )
            } catch (e: Exception) {
                viewState
            }
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