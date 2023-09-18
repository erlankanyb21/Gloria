package more

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cart.models.CartEvent
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import more.more_views.ExpandableCard
import components.GradientButton
import components.ToolBar
import more.profile.ProfileAction
import more.profile.ProfileEvent
import more.profile.ProfileViewModel
import navigation.NavigationTree
import org.tbm.gloria.main.compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
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
    StoredViewModel(factory = { ProfileViewModel() }) { viewModel ->
        val rootController = LocalRootController.current
        val viewAction = viewModel.viewActions().observeAsState()
        GradientButton(
            text = stringResource(R.string.contacts_and_address),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 7.dp)
                .height(50.dp),
            onClick = {
                viewModel.obtainEvent(ProfileEvent.OpenQAClick)
            }
        )
        GradientButton(
            text = stringResource(R.string.questions_answers),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 7.dp)
                .height(50.dp),
            onClick = {
                viewModel.obtainEvent(ProfileEvent.OpenFAQClick)
            }
        )
        GradientButton(
            text = stringResource(R.string.sign_in_or_sign_up),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 7.dp)
                .height(50.dp)
        )

        when(viewAction.value) {
            ProfileAction.OpenFAQ -> {
                rootController.present(NavigationTree.Main.FAQScreen.name)
            }
            ProfileAction.OpenQA -> {
                rootController.present(NavigationTree.Main.ContactsAndAddress.name)
            }
            null -> {}
        }
    }
}