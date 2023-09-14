package more.faq

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.BasicExpandableCard
import components.ToolBar
import org.tbm.gloria.main.compose.R.string.questions_answers
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FAQScreen() {
    val rootController = LocalRootController.current

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
            items(50) {
                BasicExpandableCard(title = "Вопрос ${it + 1}") {
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
                        text = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id es",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 27.sp,
                            fontWeight = FontWeight(400),
                            color = Color.Black,
                        )
                    )
                }
            }
        }
    }
}