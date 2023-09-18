package cart.cart_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.color

@Composable
fun AlertDialogScreen(
    text: String,
    positiveButtonText: String,
    onPositiveClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color.purple200),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color.purple500),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = text, fontSize = 20.sp,
                color = color.white)
            IconButton(
                onClick = {
                    onCloseClick.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    tint = color.white,
                    contentDescription = null)
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(color.purple500),
            onClick = {
                onPositiveClick()
                onCloseClick()
            }
        ) {
            Text(
                text = positiveButtonText,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = color.white
                )
            )
        }
    }
}