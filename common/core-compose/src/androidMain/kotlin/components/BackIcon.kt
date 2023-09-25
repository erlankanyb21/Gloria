package components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import org.tbm.gloria.core_compose.R
import theme.color

@Composable
fun BackIcon(
    iconTint: Color = color.white,
//    paddingValues: PaddingValues = PaddingValues(),
    onClick: () -> Unit
) {
    IconButton(
//        modifier = Modifier.padding(paddingValues),
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_back_arrow,
            ),
            contentDescription = null,
            tint = iconTint
        )
    }
}