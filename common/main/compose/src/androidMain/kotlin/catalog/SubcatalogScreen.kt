package catalog

import NavigationTree
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.SubcategoriesItem
import components.ToolBarWithSearch
import extensions.OnBackPress
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubcatalogScreen(slug: String) {
    StoredViewModel(factory = { CatalogViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val rootController = LocalRootController.current
        OnBackPress { rootController.popBackStack() }
        LaunchedEffect(key1 = Unit) {
            viewModel.getSubCatalog(slug)
        }
        Scaffold(
            topBar = {
                viewState.value.subCatalogItem?.let {
                    ToolBarWithSearch(
                        title = it.name,
                        backIcon = {
                            Icon(
                                tint = Color.White,
                                modifier = Modifier.clickable {
                                    rootController.popBackStack()
                                },
                                painter = painterResource(id = R.drawable.ic_back_arrow),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp),
                    columns = GridCells.Fixed(count = 1),
                    verticalArrangement = Arrangement.spacedBy(space = 10.dp),
                    contentPadding = PaddingValues(all = 10.dp)
                ) {
                    if (viewState.value.subCatalogItem != null) {
                        items(items = viewState.value.subCatalogItem!!.subcategories!!) { item ->
                            SubcategoriesItem(
                                text = item.name,
                                painterResource(id = R.drawable.gloria),
                                onClick = {
                                    rootController.push(NavigationTree.Main.CatalogDetailScreen.name)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}