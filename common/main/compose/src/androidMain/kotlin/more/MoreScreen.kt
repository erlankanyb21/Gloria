package more

import NavigationTree
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.GradientButton
import components.ToolBar
import more.more_views.ExpandableCard
import more.profile.ProfileAction
import more.profile.ProfileEvent
import more.profile.ProfileViewModel
import org.tbm.gloria.main.compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
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
        val viewState by viewModel.viewStates().observeAsState()
        var showAlert by remember { mutableStateOf(false) }

        if (showAlert) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(text = "Внимание!")
                },
                text = {
                    Text(
                        "Вы действительно хотите удалить аккаунт?\n " +
                                "После удаления аккаунта вас перебросит на регистрационную форму"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showAlert = false
                            viewModel.obtainEvent(ProfileEvent.DeleteAccount)
                        }
                    ) {
                        Text("ДА")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showAlert = false
                        }
                    ) {
                        Text("НЕТ")
                    }
                }
            )
        }

        GradientButton(
            text = stringResource(R.string.contacts_and_address),
            fontSize = 16.sp,
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
            fontSize = 16.sp,
            onClick = {
                viewModel.obtainEvent(ProfileEvent.OpenFAQClick)
            }
        )
        GradientButton(
            text = stringResource(R.string.delete_account),
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 7.dp)
                .height(50.dp),
            onClick = {
                showAlert = true
            }
        )

        when (viewAction.value) {
            ProfileAction.OpenFAQ -> {
                rootController.present(NavigationTree.Main.FAQ.name)
            }

            ProfileAction.OpenQA -> {
                rootController.present(NavigationTree.Main.ContactsAndAddress.name)
            }

            ProfileAction.OpenSignUp -> {
                rootController.present(NavigationTree.Auth.SignUp.name)
            }

            else -> {}
        }

        when (viewState.isAccountDeleted) {
            true -> {
                Toast.makeText(LocalContext.current, "Account deleted", Toast.LENGTH_SHORT)
                    .show()
            }

            false -> {
                Toast.makeText(LocalContext.current, "Account not deleted", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {}
        }
    }
}