package signin

import androidx.compose.runtime.Composable
import authorization.LoginAction
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import viewModel.LoginViewModel


class SignInView() {
    @Composable
    fun Content() {
        val rootController = LocalRootController.current
        StoredViewModel(factory = { LoginViewModel() }) { viewModel ->
            val state = viewModel.viewStates().observeAsState()
            val action = viewModel.viewActions().observeAsState()

            SignIn(state.value) {
                viewModel.obtainEvent(it)
            }

            when (action.value) {
                is LoginAction.OpenMainFlow -> {
                    rootController.findRootController().present(
                        NavigationTree.Main.MainScreen.name,
                        launchFlag = LaunchFlag.SingleNewTask
                    )
                }

                is LoginAction.OpenRegistrationScreen -> {
                    rootController.present(NavigationTree.Auth.SignUp.name)
                }

                is LoginAction.OpenForgotPasswordScreen -> {
                    rootController.present(NavigationTree.Auth.PasswordRecovery.name)
                }

                null -> {}
            }
        }
    }
}