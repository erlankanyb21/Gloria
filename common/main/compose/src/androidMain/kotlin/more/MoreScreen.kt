package more

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.ExpandableCard
import components.GradientButton
import components.ToolBar
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.gloriaGradient

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoreScreen() {
    val rootController = LocalRootController.current

    Scaffold(
        topBar = {
            ToolBar(
                title = "Eще"
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ExpandableCard(title = "Личные данные")

            OutlinedButtons()

            FilledButtons()
        }
    }
}

@Composable
private fun OutlinedButtons() {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
            .border(2.dp, gloriaGradient, RoundedCornerShape(28.dp)),
        shape = RoundedCornerShape(28.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = "Сменить пароль",
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
            .border(2.dp, gloriaGradient, RoundedCornerShape(28.dp)),
        shape = RoundedCornerShape(28.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = "История заказов",
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
            .border(2.dp, gloriaGradient, RoundedCornerShape(28.dp)),
        shape = RoundedCornerShape(28.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = "Избранное",
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun FilledButtons() {
    GradientButton(
        text = "Контакты и адреса",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
    )
    GradientButton(
        text = "Вопросы-ответы",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
    )
    GradientButton(
        text = "Войти / Зарегистрироваться",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
    )
}
