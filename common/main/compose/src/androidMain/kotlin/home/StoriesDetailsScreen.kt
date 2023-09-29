package home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import extensions.OnBackPress
import home.models.HomeEvent
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("SuspiciousIndentation")
@Composable
fun StoriesDetailsScreen(id: Int) {
    val rootController = LocalRootController.current
    OnBackPress { rootController.popBackStack() }
    StoredViewModel(factory = { HomeViewModel() }) { viewModel ->
        viewModel.obtainEvent(HomeEvent.StoriesDetailsDownload(id))
        val viewState = viewModel.viewStates().observeAsState()
        val modifiedLink = viewState.value.storiesDetails.replace("http://", "https://")
        AsyncImage(
            model = modifiedLink,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}