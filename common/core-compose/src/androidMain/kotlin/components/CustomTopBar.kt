package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.color

@Composable
fun CustomTopBar(
    leftIcon: Int? = null,
    centerText: String? = null,
    rightText: String? = null,
    colorIcon: Color? = null,
    colorText: Color? = null,
    onLeftIconClick: () -> Unit = {},
    onRightIconClick: () -> Unit = {},
) {
    TopAppBar(
        backgroundColor = color.white,
        contentColor = colorText ?: color.white,
        elevation = 0.dp,
        contentPadding = AppBarDefaults.ContentPadding,
        modifier = Modifier.fillMaxWidth().border(0.dp, Color.Transparent),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leftIcon != null) {
                IconButton(onClick = onLeftIconClick, modifier = Modifier.padding(start = 16.dp)) {
                    Icon(
                        painter = painterResource(leftIcon),
                        contentDescription = "Left Icon",
                        tint = colorIcon ?: color.black
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }

            if (centerText != null) {
                Text(
                    text = centerText,
                    color = colorText ?: color.black,
                    fontSize = 14.sp
                )
            }

            if (rightText != null) {
                IconButton(onClick = onRightIconClick,modifier = Modifier.padding(end = 16.dp)) {
                    Text(
                        text = rightText,
                        color = colorText ?: color.black,
                        fontSize = 14.sp
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }
    }
}