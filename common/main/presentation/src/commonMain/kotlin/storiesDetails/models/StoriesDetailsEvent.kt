package storiesDetails.models

sealed class StoriesDetailsEvent {
    data class DownloadStories(val id: Int): StoriesDetailsEvent()
}