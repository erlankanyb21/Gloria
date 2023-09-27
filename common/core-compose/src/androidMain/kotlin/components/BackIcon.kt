package components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import org.tbm.gloria.core_compose.R
import theme.color

@Composable
fun BackIcon(
    modifier: Modifier = Modifier,
    iconTint: Color = color.white,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
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