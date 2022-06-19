package loc.example.dev.sportstickerapp061922.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sport(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)