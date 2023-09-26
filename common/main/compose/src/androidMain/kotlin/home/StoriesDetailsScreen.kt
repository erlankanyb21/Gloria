package home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel

@Composable
fun StoriesDetailsScreen() {
    StoredViewModel({ HomeViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(model = "", contentDescription = null, modifier = Modifier.fillMaxSize())
        }
    }
}