package loc.example.dev.sportstickerapp061922.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import loc.example.dev.sportstickerapp061922.db.entity.Team
import loc.example.dev.sportstickerapp061922.repo.TeamRepo

private const val TAG = "TeamViewModel"

class TeamViewModel(private val teamRepo: TeamRepo) : ViewModel() {

    fun onItemClick(v: View?, item: Team) {
        Log.d(TAG, "onItemClick: $item")
        item.copy(isFavorite = !item.isFavorite).also {
            viewModelScope.launch {
                teamRepo.toggleFavorite(it)
            }
        }
    }
}