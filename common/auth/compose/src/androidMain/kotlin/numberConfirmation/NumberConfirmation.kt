package numberConfirmation

import NavigationTree
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.CustomTopBar
import components.GradientButton
import components.InputField
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@Composable
fun NumberConfirmation(){
    var inputValue by remember { mutableStateOf("") }
    val rootController = LocalRootController.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color.white)
    ) {


        Spacer(modifier = Modifier.height(16.dp))

        CustomTopBar(
            leftIcon = R.drawable.back,
            colorIcon = color.royalBlue,
            onLeftIconClick = {
                rootController.present(NavigationTree.Auth.SignUp.name)
            }
        )


        Spacer(modifier = Modifier.height(26.dp))

        Text(text = "Подтвердить номер \n телефона!".uppercase(), style = TextStyle(
            fontSize = 20.sp,
            color = color.purple,
            textAlign = TextAlign.Center
        )
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(text = "Текстовое сообщение с кодом проверки  \n отправлено на +996 ххх ххх 666".uppercase(), style = TextStyle(
            fontSize = 14.sp,
            color = color.black,
            textAlign = TextAlign.Center
        )
        )
        Spacer(modifier = Modifier.height(26.dp))

        Text(text = "Введите код:".uppercase(), style = TextStyle(
            fontSize = 14.sp,
            color = color.black,
            textAlign = TextAlign.Center
        )
        )

        InputField(
            value = inputValue,
            length = 5,
            modifier = Modifier.padding(16.dp),
            onValueChange = {
                inputValue = it
            },
            enabled = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions.Default
        )

        Text(text = "Отправить код повторно", style = TextStyle(
            fontSize = 14.sp,
            color = color.black,
            textAlign = TextAlign.Center
        )
        )

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
fun NumberConfirmationPreview(){
    NumberConfirmation()
}