package home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import home.models.HomeAction
import home.models.HomeEvent
import org.tbm.gloria.main.compose.R
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.gloriaGradient

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    StoredViewModel({ HomeViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(
                        brush = gloriaGradient
                    )
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.hi), style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 28.8.sp,
                                fontWeight = FontWeight(700),
                                color = Color.White,
                            )
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                StoriesItem(viewState.value) { event ->
                    viewModel.obtainEvent(event)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.sales_hits), style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.8.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000)
                ), modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            SalesHitsItem(viewState.value) { event ->
                viewModel.obtainEvent(event)
            }
            Spacer(modifier = Modifier.height(20.dp))
            AdvertisingBannerItem(viewState.value)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFF000000))
            ) {
                Text(
                    text = stringResource(id = R.string.contacts_and_address),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF552180),
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { viewModel.obtainEvent(HomeEvent.AnswersAndQuestionsClick) },
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFF000000))
            ) {
                Text(
                    text = stringResource(id = R.string.questions_answers),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF552180),
                    )
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        val rootController = LocalRootController.current
        when (viewAction.value) {
            is HomeAction.OpenStoriesDetails -> {}

            is HomeAction.OpenSalesHits -> {}
            is HomeAction.OpenContactsAndAddresses -> {}
            is HomeAction.OpenFAQ -> {
                rootController.push(screen = NavigationTree.Main.FAQ.name)
            }

            else -> {}
        }
    }
}
