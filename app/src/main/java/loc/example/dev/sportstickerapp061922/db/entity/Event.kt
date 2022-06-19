package loc.example.dev.sportstickerapp061922.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name = "home_team_id") val homeTeamId: Int,
    @ColumnInfo(name = "away_team_id") val awayTeamId: Int,
    @ColumnInfo(name = "event_date") val eventDate: Long,
    @ColumnInfo(name = "is_live") val isLive: Boolean
)
