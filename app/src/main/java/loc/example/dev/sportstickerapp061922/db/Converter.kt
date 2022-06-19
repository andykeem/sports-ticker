package loc.example.dev.sportstickerapp061922.db

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class Converter {
    @TypeConverter
    fun timestampToDate(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    @TypeConverter
    fun dateToTimestamp(value: Instant?): Long? {
        return value?.let { value.epochSeconds }
    }
}