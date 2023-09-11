package signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.color

@Composable
fun SignIn(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color.white)
    ) {
        Text(text = "Привет с возвращением!")

        OutlinedTextField( modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .height(56.dp),
            value = String(),
            placeholder = { Text(text = "Введите номер телефона")},
            shape = RoundedCornerShape(16.dp),
            onValueChange = {})

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField( modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .height(56.dp),
            value = String(),
            placeholder = { Text(text = "Введите пароль")},
            shape = RoundedCornerShape(16.dp),
            onValueChange = {}
        )

    }

}

@Preview
@Composable
fun SignInPreview(){
    SignIn()
}