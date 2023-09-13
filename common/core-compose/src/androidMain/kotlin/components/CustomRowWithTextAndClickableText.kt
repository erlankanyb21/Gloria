package companent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.color

@Composable
fun CustomRowWithTextAndClickableText(
    onClick: () -> Unit,
    text: String,
    clickableText: AnnotatedString,
    textColor: Color,
    clickableTextColor: Color,
    textSize: TextUnit
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = textSize
            )

            Spacer(modifier = Modifier.width(8.dp))

            ClickableText(
                text = clickableText,
                onClick = {
                    onClick()
                },
                style = TextStyle(color = clickableTextColor, fontSize = textSize)
            )
        }
}

@Preview
@Composable
fun PreviewCustomRowWithTextAndClickableText() {
    CustomRowWithTextAndClickableText(
        onClick = {},
        text = "Ещё нет аккаунта? ",
        clickableText = AnnotatedString("Зарегистрироваться"),
        textColor = color.black,
        clickableTextColor = color.royalBlue,
        textSize = 16.sp
    )
}