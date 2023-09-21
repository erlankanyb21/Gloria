package signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import authorization.LoginEvent
import authorization.LoginViewState
import companent.CustomRowWithTextAndClickableText
import components.PlaceholderTransformation
import components.GradientButton
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@Composable
fun SignIn(state: LoginViewState, eventHandler: (LoginEvent) -> Unit) {
    var numberPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val rootController = LocalRootController.current



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color.white)
    ) {

        Spacer(modifier = Modifier.height(26.dp))

        Text(text = "Привет \n с возвращением!".uppercase(), style = TextStyle(
            fontSize = 20.sp,
            color = color.textColor,
            textAlign = TextAlign.Center
        ))

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth().padding(start = 16.dp, end = 16.dp).height(57.dp),
            value = state.phone_number,
            enabled = !state.isSending,
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                eventHandler.invoke(LoginEvent.PhoneNumberChanged(it))
            },
            label = { Text("Номер телефона", color = color.black) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
            placeholder = {
                Text("Введите номер телефона", color = color.black)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth().padding(start = 16.dp, end = 16.dp).height(57.dp),
            value = state.password,
            enabled = !state.isSending,
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                eventHandler.invoke(LoginEvent.PasswordChanged(it))
            },
            label = { Text("Пароль", color = color.black) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
            visualTransformation = if (state.passwordHidden){
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            placeholder = {
                Text("Введите пароль", color = color.black)
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        eventHandler.invoke(LoginEvent.PasswordShowClick)
                    },
                    imageVector = if (state.passwordHidden) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    },
                    contentDescription = "Password hidden",
                    tint = color.black
                )
            }

        )

        Spacer(modifier = Modifier.height(12.dp))

        ClickableText(
            text = AnnotatedString("Забыли пароль?",) ,
            onClick = {
                rootController.present(NavigationTree.Auth.PasswordRecovery.name)
            })

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            text = "Вход",
            onClick = {
                eventHandler.invoke(LoginEvent.LoginClick)
            }
        )

        CustomRowWithTextAndClickableText(
            onClick = {
                eventHandler.invoke(LoginEvent.RegistrationClick)
            },
            text = "Ещё нет аккаунта? ",
            clickableText = AnnotatedString("Зарегистрироваться"),
            textColor = color.black,
            clickableTextColor = color.royalBlue,
            textSize = 16.sp
        )

        Spacer(modifier = Modifier.height(46.dp))

    }

}

//@Preview
//@Composable
//fun SignInPreview(){
//    SignIn()
//}