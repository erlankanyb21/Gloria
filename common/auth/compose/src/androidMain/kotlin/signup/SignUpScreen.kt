package signup

import NavigationTree
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import authorization.signUp.SignUpAction
import authorization.signUp.SignUpEvent
import authorization.signUp.SignUpViewState
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.GradientButton
import components.PlaceholderTransformation
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color
import viewModel.signUp.SignUpViewModel

@Composable
fun SignUpScreen() {
    val rootController = LocalRootController.current
    val context = LocalContext.current
    StoredViewModel({ SignUpViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        var checked by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color.white)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Пропустить", style = TextStyle(
                        fontSize = 14.sp,
                        color = color.black,
                        textDecoration = TextDecoration.Underline,
                    )
                )
            }

            Spacer(modifier = Modifier.height(33.dp))

            Text(
                text = "привет! \n создай свой аккаунт".uppercase(), style = TextStyle(
                    fontSize = 20.sp, color = color.textColor, textAlign = TextAlign.Center
                ), modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp),
                value = state.value.fullName,
                enabled = !state.value.isSending,
                shape = RoundedCornerShape(16.dp),
                onValueChange = {
                    if (it.length <= 20) viewModel.obtainEvent(SignUpEvent.FullNameChanged(it))
                },
                label = { Text("Имя", color = color.black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = color.black,
                    unfocusedBorderColor = color.black,
                    cursorColor = color.black,
                    textColor = color.black
                ),
                visualTransformation = if (state.value.fullName.isEmpty()) PlaceholderTransformation(
                    "Введите имя"
                )
                else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next
                ),
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp),
                value = state.value.phoneNumber,
                shape = RoundedCornerShape(16.dp),
                enabled = !state.value.isSending,
                isError = !state.value.phoneNumber.startsWith("+996") && state.value.phoneNumber.isNotEmpty(),
                onValueChange = {
                    if (it.length <= 13) viewModel.obtainEvent(SignUpEvent.PhoneNumberChanged(it))
                },
                label = { Text("Номер телефона") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = color.black,
                    unfocusedBorderColor = color.black,
                    cursorColor = color.black,
                    textColor = color.black,
                    errorBorderColor = color.errorMessage,
                    errorLabelColor = color.errorMessage,
                    errorCursorColor = color.errorMessage,
                    disabledLabelColor = color.black,
                    focusedLabelColor = color.black,
                    unfocusedLabelColor = color.black
                ),
                visualTransformation = if (state.value.phoneNumber.isEmpty()) PlaceholderTransformation(
                    "Введите номер телефона"
                )
                else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next
                )
            )

            ValidatePhoneNumber(state)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp),
                value = state.value.password,
                enabled = !state.value.isSending,
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            viewModel.obtainEvent(SignUpEvent.PasswordShowClick)
                        }, imageVector = if (state.value.passwordHidden) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }, contentDescription = "Показать пароль", tint = color.black
                    )
                },
                onValueChange = {
                    viewModel.obtainEvent(SignUpEvent.PasswordChanged(it))
                },
                label = { Text("Пароль", color = color.black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = color.black,
                    unfocusedBorderColor = color.black,
                    cursorColor = color.black,
                    textColor = color.black
                ),
                visualTransformation = if (state.value.passwordHidden) PasswordVisualTransformation()
                else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp),
                value = state.value.passwordConfirm,
                shape = RoundedCornerShape(16.dp),
                enabled = !state.value.isSending,
                onValueChange = {
                    viewModel.obtainEvent(SignUpEvent.PasswordConfirmChanged(it))
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            viewModel.obtainEvent(SignUpEvent.PasswordConfirmShowClick)
                        }, imageVector = if (state.value.passwordConfirmHidden) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }, contentDescription = "Показать подтверждение пароля", tint = color.black
                    )
                },
                label = { Text("Пароль", color = color.black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = color.black,
                    unfocusedBorderColor = color.black,
                    cursorColor = color.black,
                    textColor = color.black
                ),
                visualTransformation = if (state.value.passwordConfirmHidden) PasswordVisualTransformation()
                else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(12.dp))

            val customCheckboxColors = CheckboxDefaults.colors(
                checkedColor = color.textColor,
                uncheckedColor = color.textColor,
                checkmarkColor = color.white
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = checked, onCheckedChange = { newChecked ->
                        checked = newChecked
                    }, colors = customCheckboxColors
                )

                Text(text = "Я принимаю условия пользования и договор оферты")
            }

            Spacer(modifier = Modifier.weight(1f))

            GradientButton(modifier = Modifier.fillMaxWidth(),
                text = "Регистрация",
                fontSize = 18.sp,
                onClick = {
                    when (checked) {
                        true -> viewModel.obtainEvent(SignUpEvent.RegistrationClick)
                        false -> showToast(context)
                    }
                })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Уже есть аккаунт?", style = TextStyle(
                        fontSize = 14.sp,
                        color = color.black,
                    )
                )

                TextButton(
                    onClick = { rootController.present(screen = NavigationTree.Auth.SignIn.name) },
                ) {
                    Text(
                        text = "Войти", style = TextStyle(
                            fontSize = 14.sp,
                            color = color.royalBlue,
                            textDecoration = TextDecoration.Underline,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(46.dp))
        }

        when (action.value) {
            is SignUpAction.OpenMainFlow -> {
                rootController.present(screen = NavigationTree.Main.MainScreen.name)
            }

            is SignUpAction.OpenSignIn -> {
                rootController.present(screen = NavigationTree.Auth.SignIn.name)
            }

            else -> {}
        }
    }
}

@Composable
fun ValidatePhoneNumber(state: State<SignUpViewState>) {
    AnimatedVisibility(visible = !state.value.phoneNumber.startsWith("+996") && state.value.phoneNumber.isNotEmpty()) {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Формат номера должен быть +996",
                color = color.errorMessage,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

private fun showToast(context: Context) {
    Toast.makeText(context, "Согласитесь с условиями!", Toast.LENGTH_SHORT).show()
}