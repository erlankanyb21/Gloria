package catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.ToolBarWithSearch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatalogScreen() {
    Scaffold (
        topBar = {
            ToolBarWithSearch(title = "Каталог", painter = null, onClick = Unit)
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize().padding(top = it.calculateTopPadding() + 16.dp)
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
                items(count = 6) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(size = 4.dp)),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Image(
                            painter = painterResource(
                                id = org.tbm.gloria.core_compose.R.drawable.balls,
                            ),
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            modifier = Modifier
                                .padding(end = 10.dp, bottom = 10.dp),
                            text = "Шары",
                            style = TextStyle(Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}
