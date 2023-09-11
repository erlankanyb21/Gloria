package more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import companent.ToolBar
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun MoreScreen() {
    val rootController = LocalRootController.current
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ToolBar(
            title = "Eще"
        )
    }


}