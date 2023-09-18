package catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.CardProduct
import components.ToolBarWithSearch
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun CatalogDetailScreen() {

    val rootController = LocalRootController.current
    Scaffold(
        topBar = {
            ToolBarWithSearch(
                title = stringResource(id = R.string.catalog),
                backIcon = {
                    Image(
                        modifier = Modifier.clickable {
                            rootController.popBackStack()
                        },
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = null,
                    )
                }
            )
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
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(space = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 5.dp),
                contentPadding = PaddingValues(all = 5.dp)
            ) {
                items(count = 10) {
                    CardProduct()
                }
            }
        }
    }
}