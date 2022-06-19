package loc.example.dev.sportstickerapp061922.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import loc.example.dev.sportstickerapp061922.db.entity.Team

@Dao
interface TeamDao {
    @Query("SELECT * FROM team")
    fun getAll(): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg teams: Team)

    @Delete
    fun delete(vararg teams: Team)

    @Query("SELECT t.* FROM team t INNER JOIN sport s ON (t.sport_id = s.id) WHERE s.name LIKE :sport")
    fun getTeamsLikeSport(sport: String): Flow<List<Team>>

    @Update
    fun update(vararg teams: Team)
}