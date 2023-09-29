package more.favorite

import models.home.Favorite

data class FavoriteViewState (
    val favorite: List<Favorite> = emptyList()
)
