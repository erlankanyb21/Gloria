package passwordRecovery

import NavigationTree
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.CustomTopBar
import components.GradientButton
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@Composable
fun PasswordRecoverySms() {
    val rootController = LocalRootController.current
    var smsResult by remember { mutableStateOf("") }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color.white)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        CustomTopBar(
            leftIcon = R.drawable.back,
            colorIcon = color.black,
            onLeftIconClick = {
                rootController.present(NavigationTree.Auth.SignUp.name)
            }
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(text = "Восстановление пароля".uppercase(), style = TextStyle(
            fontSize = 20.sp,
            color = color.purple,
            textAlign = TextAlign.Center
        )
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = smsResult,
            onValueChange = { smsResult = it },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            shape = CircleShape,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            placeholder = { Text(text = "Введите код") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
        )

        Row {

            Icon(
                painter = painterResource(R.drawable.arrowclockwise),
                contentDescription = "Left Icon",
                tint = color.black
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(text = "Другой код")
        }


        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            text = "Вход",
            fontSize = 18.sp)

        Spacer(modifier = Modifier.height(98.dp))

    }
}

@Preview
@Composable
fun PasswordRecoverySmsPreview(){
    PasswordRecoverySms()
}