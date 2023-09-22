package home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import extensions.OnBackPress
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun StoriesDetailsScreen() {
    val rootController = LocalRootController.current
    OnBackPress { rootController.popBackStack() }
    StoredViewModel({ HomeViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        AsyncImage(
            model = viewState.value.storiesDetails,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}