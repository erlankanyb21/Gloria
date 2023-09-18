package home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import home.models.HomeViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdvertisingBannerItem(viewState: HomeViewState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                pageCount = viewState.advertisingBanner.size,
                state = pagerState
            ) {
                val originLink = viewState.advertisingBanner[it].image
                val modifiedLink = originLink.replace("http://", "https://")
                AsyncImage(
                    model = modifiedLink,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            if (viewState.advertisingBanner.isNotEmpty()) {
                WormPageIndicator(
                    totalPages = viewState.advertisingBanner.size,
                    currentPage = pagerState.currentPage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 10.dp)
                )
            }
        }
    }
}