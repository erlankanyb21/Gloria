package catalog

import NavigationTree
import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catalog.models.CatalogAction
import catalog.models.CatalogEvent
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.ToolBarWithSearch
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatalogScreen() {

    val rootController = LocalRootController.current
    StoredViewModel(factory = { CatalogViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()


        Scaffold(topBar = {
            ToolBarWithSearch(title = stringResource(id = R.string.catalog))
        }) {
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
                        columns = GridCells.Fixed(count = 2),
                        verticalArrangement = Arrangement.spacedBy(space = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 5.dp),
                        contentPadding = PaddingValues(all = 5.dp)
                    ) {
                        items(items = viewState.value.catalogItem) { item ->
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .shimmerEffect()
                                .height(180.dp)
                                .clickable {
                                    if (item.subcategories.isNullOrEmpty()) {
                                        viewModel.obtainEvent(CatalogEvent.OpenProductClick)
                                    } else {
                                        viewModel.obtainEvent(
                                            CatalogEvent.OpenSubCatalogClick(
                                                item.categorySlug
                                            )
                                        )
                                    }
                                }
                                .clip(RoundedCornerShape(size = 4.dp)),
                                contentAlignment = Alignment.BottomEnd) {
                                AsyncImage(
                                    modifier = Modifier.fillMaxSize(),
                                    model = ImageRequest.Builder(context = LocalContext.current)
                                        .data(item.image?.replace("http", "https")).crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    modifier = Modifier.padding(end = 10.dp, bottom = 10.dp),
                                    text = item.name,
                                    style = TextStyle(
                                        Color.Black,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
        when (viewAction.value) {
            CatalogAction.OpenProduct -> {
                rootController.findRootController().present(
                    screen = NavigationTree.Main.MainFlow.name,
                    startScreen = NavigationTree.Main.CatalogDetailScreen.name
                )
            }

            is CatalogAction.OpenSubCatalog -> {
                    rootController.findRootController().present(
                    screen = NavigationTree.Main.MainFlow.name,
                    startScreen = NavigationTree.Main.Subcatalog.name,
                    params = (viewAction.value as CatalogAction.OpenSubCatalog).slag
                )
            }

            else -> {}
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = ""
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFF1EFEF), Color(0xFFFFFFFF), Color(0xFFF1EFEF)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}