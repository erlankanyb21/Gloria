package more.faq

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
                    title = stringResource(id = R.string.questions_answers),
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
                        TextAnimation(text = items.description)
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

@Composable
fun TextAnimation(text: String) {
    val animatedProgress = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(1f, animationSpec = tween(durationMillis = 8000))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.take((text.length * animatedProgress.value).toInt()),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 27.sp,
                fontWeight = FontWeight(400),
                color = Color.Black
            ),
            modifier = Modifier.padding(15.dp)
        )
    }
}