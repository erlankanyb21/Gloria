package more.faq

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.ToolBar
import extensions.OnBackPress
import more.more_views.SimpleExpandableCard
import org.tbm.gloria.core_compose.R
import org.tbm.gloria.main.compose.R.string.questions_answers
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FAQScreen() {
    val rootController = LocalRootController.current
    OnBackPress { rootController.popBackStack() }
    StoredViewModel(factory = { FAQViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()

        Scaffold(
            topBar = {
                ToolBar(
                    title = stringResource(id = questions_answers),
                    backIcon = {
                        IconButton(
                            modifier = Modifier.size(50.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color.White
                            ),
                            onClick = { rootController.popBackStack() },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back_arrow),
                                contentDescription = null,
                            )
                        }
                    })
            }
        ) {
            LazyColumn(
                modifier = Modifier.padding(it),
            ) {
                items(items = viewState.value.faqItems) { items ->
                    SimpleExpandableCard(title = items.title) {
                        Text(
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
                            text = items.description,
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 27.sp,
                                fontWeight = FontWeight(400),
                                color = Color.Black,
                            )
                        )
                    }
                }

                item {
                    if (viewState.value.openWhatsApp?.whatsappUrl?.isNotEmpty() == true) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val context = LocalContext.current
                            Text(text = "Если вы не нашли ответ на свой вопрос:")
                            Spacer(modifier = Modifier.height(14.dp))
                            Image(
                                modifier = Modifier
                                    .clickable {
                                        Intent(Intent.ACTION_VIEW).also { intent ->
                                            intent.data =
                                                Uri.parse(viewState.value.openWhatsApp?.whatsappUrl)
                                            context.startActivity(intent)
                                        }
                                    },
                                painter = painterResource(id = R.drawable.whatsapp),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }
}