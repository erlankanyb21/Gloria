package passwordRecovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.CustomTopBar
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@Composable
fun CreatedNewPassword() {
    val rootController = LocalRootController.current
    var numberPhone by remember { mutableStateOf("") }


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

    }
}

@Preview
@Composable
fun CreatedNewPasswordPreview(){
    CreatedNewPassword()
}