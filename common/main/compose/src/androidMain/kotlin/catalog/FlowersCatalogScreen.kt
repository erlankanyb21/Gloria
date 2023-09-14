package catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import companent.ItemCatalog
import companent.ToolBarWithSearch
import org.tbm.gloria.core_compose.R

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FlowersCatalogScreen() {
    Scaffold(
        topBar = {
            ToolBarWithSearch(
                title = "Цветы",
                painter = painterResource(id = R.drawable.ic_back_arrow),
                onClick = Unit
            )
        }
    ) {
        Column(
            modifier = Modifier
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
            ){
                items(count = 10){
                    ItemCatalog(text = "Цветы", painterResource(id = R.drawable.gloria))
                }
            }
        }
    }
}