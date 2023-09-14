package companent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tbm.gloria.core_compose.R
import theme.gloriaGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    backIcon: (@Composable () -> Unit)? = null,
    title: String,
) {
    TopAppBar(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .background(gloriaGradient)
            .statusBarsPadding()
            .fillMaxWidth()
            .height(56.dp),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                ),
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            backIcon?.let { it() }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsTopBarPreview() {
    ToolBar(backIcon = {
        IconButton(
            modifier = Modifier
                .size(50.dp)
                .padding(top = 40.dp),
            onClick = {},
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
            )
        }
    }, title = "eoe")
}