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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.ExpandableCard
import components.GradientButton
import components.ToolBar
import navigation.NavigationTree
import org.tbm.gloria.main.compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.gloriaGradient

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoreScreen() {

    Scaffold(
        topBar = {
            ToolBar(
                title = stringResource(id = R.string.profile)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            ExpandableCard(title = stringResource(R.string.profile_data))

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
            text = stringResource(R.string.change_password),
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
            text = stringResource(R.string.order_history),
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
            text = stringResource(R.string.favorites),
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun FilledButtons() {
    val rootController = LocalRootController.current
    GradientButton(
        text = stringResource(R.string.contacts_and_address),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
    )
    GradientButton(
        text = stringResource(R.string.questions_answers),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp),
        onClick = {
            rootController.present(screen = NavigationTree.Main.FAQScreen.name)
        }
    )
    GradientButton(
        text = stringResource(R.string.sign_in_or_sign_up),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .height(50.dp)
    )
}
