package loc.example.dev.sportstickerapp061922.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import loc.example.dev.sportstickerapp061922.repo.TeamRepo
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class TickerViewModel(teamRepo: TeamRepo) : ViewModel() {
    private val termFlow = MutableStateFlow("")
    val resultLiveData = termFlow
        .filter { it.isNotBlank() && it.length > 2 }
        .debounce(1.seconds.inWholeMilliseconds)
        .flatMapLatest {
            teamRepo.searchTeam(it)
        }.asLiveData()

    fun observeSearchTerm(term: String) {
        termFlow.update { term }
    }
}