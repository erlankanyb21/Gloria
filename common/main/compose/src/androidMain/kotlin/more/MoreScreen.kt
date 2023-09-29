package more

import NavigationTree
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.ToolBar
import more.more_views.ExpandableCard
import more.profile.ProfileAction
import more.profile.ProfileEvent
import more.profile.ProfileViewModel
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.gloriaGradient

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoreScreen() {
    StoredViewModel(factory = { ProfileViewModel() }) { viewModel ->
        val rootController = LocalRootController.current
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()
        when (viewState.value.getProfileResponse?.fullname.toString().isNotEmpty()) {
            true -> {
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

                        OutlinedButtons {
                            viewModel.obtainEvent(it)
                        }

                        FilledButtons {
                            viewModel.obtainEvent(it)
                        }
                    }
                }
            }

            else -> {
                rootController.popBackStack()
            }
        }

        when (viewAction.value) {
            ProfileAction.OpenFAQ -> rootController.present(NavigationTree.Main.FAQ.name)
            ProfileAction.OpenQA -> rootController.present(NavigationTree.Main.ContactsAndAddress.name)
            ProfileAction.OpenFavorite -> rootController.present(NavigationTree.Main.Favorite.name)
            else -> {}
        }
    }
}

@Composable
private fun OutlinedButtons(eventHandler: (ProfileEvent) -> Unit) {
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
        onClick = { eventHandler(ProfileEvent.OpenFavorite) }
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
fun FilledButtons(eventHandler: (ProfileEvent) -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(gloriaGradient),
        onClick = {
            eventHandler(ProfileEvent.OpenQAClick)
        },
    ) {
        Text(
            text = stringResource(R.string.contacts_and_address),
            fontSize = 16.sp,
        )
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(gloriaGradient),
        onClick = {
            eventHandler(ProfileEvent.OpenFAQClick)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(
            text = stringResource(R.string.questions_answers),
            fontSize = 16.sp,
        )
    }
}