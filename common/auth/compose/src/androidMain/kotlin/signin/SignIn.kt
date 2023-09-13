package signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import companent.CustomRowWithTextAndClickableText
import companent.GradientButton
import companent.PlaceholderTransformation
import theme.color

@Composable
fun SignIn(){
    var numberPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


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
            value = numberPhone,
            shape = RoundedCornerShape(16.dp),
            onValueChange = {
                numberPhone = it
            },
            label = { Text("Пароль", color = color.black) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color.black,
                unfocusedBorderColor = color.black,
                cursorColor = color.black,
                textColor = color.black),
            visualTransformation = if (numberPhone.isEmpty())
                PlaceholderTransformation("Подтвердите пароль")
            else VisualTransformation.None,
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth().padding(start = 16.dp, end = 16.dp).height(57.dp),
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
                PlaceholderTransformation("Подтвердите пароль")
            else VisualTransformation.None,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Забыли пароль?", style = TextStyle(
            fontSize = 12.sp,
            color = color.black
        ))

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            text = "Вход")

        CustomRowWithTextAndClickableText(
            onClick = {},
            text = "Ещё нет аккаунта? ",
            clickableText = AnnotatedString("Зарегистрироваться"),
            textColor = color.black,
            clickableTextColor = color.royalBlue,
            textSize = 16.sp
        )

        Spacer(modifier = Modifier.height(46.dp))

    }

}

@Preview
@Composable
fun SignInPreview(){
    SignIn()
}