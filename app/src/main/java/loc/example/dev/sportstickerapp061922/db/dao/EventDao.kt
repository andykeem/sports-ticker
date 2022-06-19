package loc.example.dev.sportstickerapp061922.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import loc.example.dev.sportstickerapp061922.db.entity.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getAll(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg events: Event)

    @Delete
    fun delete(vararg events: Event)
}