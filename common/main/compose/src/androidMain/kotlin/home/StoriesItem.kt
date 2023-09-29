package home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import home.models.HomeEvent
import home.models.HomeViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoriesItem(viewState: HomeViewState, eventHandler: (HomeEvent) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(viewState.stories.size) {
            Row(
                Modifier.animateItemPlacement(
                    tween(durationMillis = 250)
                )
            ) {
                val id = viewState.stories[it].id
                val originLink = viewState.stories[it].image
                val modifiedLink = originLink?.replace("http://", "https://")
                AsyncImage(
                    model = modifiedLink,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .drawBehind { drawCircle(Color(0xFFFFFFFF)) }
                        .padding(2.dp)
                        .clip(CircleShape)
                        .clickable {
                            id?.let {
                                eventHandler(HomeEvent.StoriesDetailsClick(id))
                            }
                        }
                )
            }
        }
    }
}
