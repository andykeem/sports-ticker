package loc.example.dev.sportstickerapp061922.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import loc.example.dev.sportstickerapp061922.db.entity.Sport

@Dao
interface SportDao {
    @Query("SELECT * FROM sport")
    fun getAll(): Flow<List<Sport>>

    @Query("SELECT * FROM sport WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): Flow<Sport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg sports: Sport)

    @Delete
    fun delete(sport: Sport)
}