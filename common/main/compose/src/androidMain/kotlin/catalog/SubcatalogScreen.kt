package catalog

import NavigationTree
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import catalog.models.CatalogAction
import catalog.models.CatalogEvent
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.SubcategoriesItem
import components.ToolBarWithSearch
import extensions.OnBackPress
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubcatalogScreen(slug: String) {
    StoredViewModel(factory = { CatalogViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()
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
                                    viewModel.obtainEvent(CatalogEvent.OnBackClick)
                                },
                                painter = painterResource(id = R.drawable.ic_back_arrow),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        ) {
            if (viewState.value.loading) {
                Box(
                    modifier = Modifier
                        .padding(top = it.calculateTopPadding())
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = color.purple200)
                }
            } else {
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
                                        viewModel.obtainEvent(CatalogEvent.OpenProductClick)
                                    }
                                )
                            }
                        }
                    }
                }
            }
            when (viewAction.value) {
                CatalogAction.OnBackClick -> {
                    rootController.popBackStack()
                }

                CatalogAction.OpenProduct -> {
                    rootController.push(NavigationTree.Main.CatalogDetailScreen.name)
                }

                else -> {}
            }
        }
    }
}