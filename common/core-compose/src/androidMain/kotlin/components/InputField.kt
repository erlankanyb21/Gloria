package components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.color

@Composable
fun InputField(
    value: String,
    length: Int,
    modifier: Modifier = Modifier,
    boxWidth: Dp = 38.dp,
    boxHeight: Dp = 38.dp,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
) {
    val spaceBetweenBoxes = 8.dp
    BasicTextField(modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = {
            if (it.length <= length) {
                onValueChange(it)
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = {
            Row(
                Modifier.size(width = (boxWidth + spaceBetweenBoxes) * length, height = boxHeight),
                horizontalArrangement = Arrangement.spacedBy(spaceBetweenBoxes),
            ) {
                repeat(length) { index ->
                    Box(
                        modifier = Modifier
                            .size(boxWidth, boxHeight)
                            .border(
                                1.dp,
                                color = color.purple,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = value.getOrNull(index)?.toString() ?: "",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        })
}
@Preview
@Composable
fun InputFieldPreview(){

    var inputValue by remember { mutableStateOf("") }

    InputField(
        value = inputValue,
        length = 5,
        modifier = Modifier.padding(16.dp),
        onValueChange = {
            inputValue = it
        },
        enabled = true,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default
    )
}