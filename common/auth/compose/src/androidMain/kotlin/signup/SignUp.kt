package signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import companent.CustomRowWithTextAndClickableText
import components.CustomTopBar
import components.PlaceholderTransformation
import components.GradientButton
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@Composable
fun SignUp(){
    var name by remember { mutableStateOf("") }
    var numberPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var checked by remember {mutableStateOf(false)}
    val rootController = LocalRootController.current



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color.white)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        CustomTopBar(
            rightText = "Пропустить",
            colorIcon = color.royalBlue,
            onRightIconClick = {
                rootController.present(NavigationTree.Main.MainScreen.name)
            }
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(text = "привет! \n создай свой аккаунт".uppercase(), style = TextStyle(
            fontSize = 20.sp,
            color = color.textColor,
            textAlign = TextAlign.Center
        ))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(57.dp),
            value = name,
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                name = it
            },
            label = { Text("Имя", color = color.black) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
            visualTransformation = if (name.isEmpty())
                PlaceholderTransformation("Введите имя")
            else VisualTransformation.None,
        )

        Spacer(modifier = Modifier.height(12.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(57.dp),
            value = numberPhone,
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                numberPhone = it
            },
            label = { Text("Номер телефона", color = color.black) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
            visualTransformation = if (numberPhone.isEmpty())
                PlaceholderTransformation("Введите номер телефона")
            else VisualTransformation.None,
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(57.dp),
            value = password,
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                password = it
            },
            label = { Text("Пароль", color = color.black) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
            visualTransformation = if (password.isEmpty())
                PlaceholderTransformation("Пароль")
            else VisualTransformation.None,
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(57.dp),
            value = confirmPassword,
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                confirmPassword = it
            },
            label = { Text("Пароль", color = color.black) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
            visualTransformation = if (confirmPassword.isEmpty())
                PlaceholderTransformation("Подтвердите пароль")
            else VisualTransformation.None,
        )

        Spacer(modifier = Modifier.height(12.dp))

        val customCheckboxColors = CheckboxDefaults.colors(
            checkedColor = color.textColor,
            uncheckedColor = color.textColor,
            checkmarkColor = color.white
        )

        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = { newChecked ->
                    checked = newChecked
                },
                colors = customCheckboxColors
            )

            Text(text = "Я принимаю условия пользования и договор оферты")
        }
        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            text = "Вход")

        CustomRowWithTextAndClickableText(
            onClick = {
                      rootController.present(NavigationTree.Auth.SignIn.name)
            },
            text = "Уже есть аккаунт?",
            clickableText = AnnotatedString("Войти"),
            textColor = color.black,
            clickableTextColor = color.royalBlue,
            textSize = 16.sp
        )

        Spacer(modifier = Modifier.height(46.dp))
    }
}

@Preview
@Composable
fun SignUpPreview(){
    SignUp()
}