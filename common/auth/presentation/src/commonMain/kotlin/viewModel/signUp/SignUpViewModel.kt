package viewModel.signUp

import AuthRepository
import authorization.signUp.SignUpAction
import authorization.signUp.SignUpEvent
import authorization.signUp.SignUpViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch

class SignUpViewModel : BaseSharedViewModel<SignUpViewState, SignUpAction, SignUpEvent>(
    initialState = SignUpViewState(
        fullName = "",
        phoneNumber = "",
        password = "",
        passwordConfirm = ""
    )
) {
    private val authRepository: AuthRepository = Inject.instance()

    init {
        checkUserLoggedIn()
    }

    override fun obtainEvent(viewEvent: SignUpEvent) {
        when (viewEvent) {
            is SignUpEvent.PasswordShowClick -> changePasswordVisibility()
            is SignUpEvent.PasswordConfirmShowClick -> changePasswordConfirmVisibility()
            is SignUpEvent.RegistrationClick -> sendRegistration()
            is SignUpEvent.FullNameChanged -> obtainFullNameChanged(viewEvent.fullName)
            is SignUpEvent.PhoneNumberChanged -> obtainPhoneNumber(viewEvent.phoneNumber)
            is SignUpEvent.PasswordChanged -> obtainPasswordChanged(viewEvent.password)
            is SignUpEvent.PasswordConfirmChanged -> obtainPasswordConfirmChanged(viewEvent.passwordChanged)
        }
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            val isUserLoggedIn = authRepository.isUserLoggedIn()
            if (isUserLoggedIn) {
                viewAction = SignUpAction.OpenMainFlow
            }
        }
    }

    private fun sendRegistration() {
        viewState = viewState.copy(isSending = true)
        withViewModelScope {
            try {
                val response = authRepository.signUp(
                    viewState.fullName,
                    viewState.phoneNumber,
                    viewState.password
                )
                when (response.code.isNotEmpty()) {
                    true -> {
                        val code = authRepository.confirmUser(response.code)
                        if (code.isNotBlank()) {
                            val login =
                                authRepository.login(viewState.phoneNumber, viewState.password)
                            if (login.detail.access.isNotBlank()) {
                                viewAction = SignUpAction.OpenMainFlow
                            }
                        }
                    }

                    false -> {
                        viewState = viewState.copy(isSending = false)
                    }
                }
            } catch (e: Exception) {
                viewState = viewState.copy(isSending = false)
            }
        }
    }

    private fun obtainFullNameChanged(fullName: String) {
        viewState = viewState.copy(
            fullName = fullName
        )
    }

    private fun obtainPhoneNumber(phoneNumber: String) {
        viewState = viewState.copy(phoneNumber = phoneNumber)
    }

    private fun obtainPasswordChanged(password: String) {
        viewState = viewState.copy(
            password = password
        )
    }

    private fun obtainPasswordConfirmChanged(passwordConfirm: String) {
        viewState = viewState.copy(
            passwordConfirm = passwordConfirm
        )
    }

    private fun changePasswordVisibility() {
        viewState = viewState.copy(passwordHidden = !viewState.passwordHidden)
    }

    private fun changePasswordConfirmVisibility() {
        viewState = viewState.copy(passwordConfirmHidden = !viewState.passwordConfirmHidden)
    }
}
