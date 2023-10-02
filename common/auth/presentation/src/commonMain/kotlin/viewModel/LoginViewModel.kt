package viewModel

import AuthRepository
import authorization.LoginAction
import authorization.LoginEvent
import authorization.LoginViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch

class LoginViewModel: BaseSharedViewModel<LoginViewState, LoginAction, LoginEvent> (
    initialState = LoginViewState(phone_number = "", password = "")
){
    private val authRepository: AuthRepository = Inject.instance()
    /*init {
        checkUserLoggedIn()
    }*/


    override fun obtainEvent(viewEvent: LoginEvent) {
        when(viewEvent){
            is LoginEvent.LoginClick -> sendLogin()
            is LoginEvent.PhoneNumberChanged -> obtainPhoneChanged(viewEvent.value)
            is LoginEvent.PasswordChanged -> obtainPasswordChanged(viewEvent.value)
            is LoginEvent.ForgotClick -> openForgot()
            is LoginEvent.RegistrationClick -> openRegistration()
            is LoginEvent.PasswordShowClick -> changePasswordVisibility()
        }
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            val isUserLoggedIn = authRepository.isUserLoggedIn()
            if (isUserLoggedIn) {
                viewAction = LoginAction.OpenMainFlow
            }
        }
    }

    private fun sendLogin(){
        viewState = viewState.copy(isSending = true)
        viewModelScope.launch {
            try {
                val response = authRepository.login(viewState.phone_number, viewState.password)
                if (response.detail.access.isNotBlank()){
                    viewState = viewState.copy(phone_number = "", password = "", isSending = false)
                    viewAction = LoginAction.OpenMainFlow
                } else {
                    viewState = viewState.copy(isSending = false)
                }
            } catch (e: Exception){
                viewState = viewState.copy(isSending = false)
            }
        }
    }

    private fun changePasswordVisibility(){
        viewState = viewState.copy(passwordHidden = !viewState.passwordHidden)
    }

    private fun openForgot(){
        viewAction = LoginAction.OpenForgotPasswordScreen
    }

    private fun openRegistration(){
        viewAction = LoginAction.OpenRegistrationScreen
    }

    private fun obtainPhoneChanged(value: String) {
        viewState = viewState.copy(
            phone_number = value
        )
    }

    private fun obtainPasswordChanged(value: String) {
        viewState = viewState.copy(
            password = value
        )
    }

}