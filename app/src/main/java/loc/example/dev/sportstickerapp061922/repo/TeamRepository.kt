package loc.example.dev.sportstickerapp061922.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import loc.example.dev.sportstickerapp061922.db.dao.TeamDao
import loc.example.dev.sportstickerapp061922.db.entity.Team

interface TeamRepo {
    fun searchTeam(term: String): Flow<List<Team>>
    suspend fun toggleFavorite(team: Team)
}

class TeamRepository(
    private val teamDao: TeamDao
) : TeamRepo {
    override fun searchTeam(term: String): Flow<List<Team>> {
        return teamDao.getTeamsLikeSport(term).map { it.sortedBy { it.name } }
    }

    override suspend fun toggleFavorite(team: Team) = withContext(Dispatchers.IO) {
        teamDao.update(team)
    }
}