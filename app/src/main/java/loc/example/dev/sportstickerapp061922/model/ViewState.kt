package loc.example.dev.sportstickerapp061922.model

sealed class ViewState<out S> {
    data class Error(val cause: Exception) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    data class Loaded<T>(val data: T) : ViewState<T>()
}
