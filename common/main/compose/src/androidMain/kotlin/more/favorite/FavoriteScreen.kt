package more.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.ToolBar
import extensions.OnBackPress
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen() {
    val rootController = LocalRootController.current
    OnBackPress { rootController.popBackStack() }
    StoredViewModel(factory = { FavoriteViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
    Scaffold(
        topBar = {
            ToolBar(
                title = stringResource(id = R.string.favorites),
                backIcon = {
                    IconButton(
                        modifier = Modifier.size(50.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        ),
                        onClick = { rootController.popBackStack() },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                        )
                    }
                })
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(space = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 5.dp),
            contentPadding = PaddingValues(all = 5.dp)
        ) {
            items(items = state.value.favorite) { item ->
                FavoriteItem(favorite = item)
            }
        }
    }}
}