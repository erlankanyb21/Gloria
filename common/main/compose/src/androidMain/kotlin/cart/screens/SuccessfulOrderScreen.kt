package cart.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.BackIcon
import components.GradientButton
import org.tbm.gloria.core_compose.R
import theme.color

@Composable
fun SuccessfulOrderScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BackIcon(
            iconTint = color.black,
//            paddingValues = PaddingValues(top = 40.dp)
        ) {

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_successful),
                contentDescription = null
            )

            Text(
                text = "Ваш заказ успешно оформлен!",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = color.black
                )
            )
        }
        GradientButton(
            text = "Перейти на главную",
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp)
        )
    }
}