package loc.example.dev.sportstickerapp061922.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import loc.example.dev.sportstickerapp061922.db.dao.EventDao
import loc.example.dev.sportstickerapp061922.db.dao.SportDao
import loc.example.dev.sportstickerapp061922.db.dao.TeamDao
import loc.example.dev.sportstickerapp061922.db.entity.Event
import loc.example.dev.sportstickerapp061922.db.entity.Sport
import loc.example.dev.sportstickerapp061922.db.entity.Team

@TypeConverters(Converter::class)
@Database(entities = [Event::class, Sport::class, Team::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun sportDao(): SportDao
    abstract fun teamDao(): TeamDao
}