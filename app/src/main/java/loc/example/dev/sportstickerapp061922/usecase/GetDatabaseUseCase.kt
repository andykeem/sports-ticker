package loc.example.dev.sportstickerapp061922.usecase

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import loc.example.dev.sportstickerapp061922.constant.DB_NAME
import loc.example.dev.sportstickerapp061922.db.AppDatabase
import loc.example.dev.sportstickerapp061922.db.entity.Event
import loc.example.dev.sportstickerapp061922.db.entity.Sport
import loc.example.dev.sportstickerapp061922.db.entity.Team
import kotlin.time.Duration.Companion.hours

interface GetDatabaseUseCase {
    operator fun invoke(): AppDatabase
}

internal class GetDatabaseUseCaseImpl(
    private val ctx: Context,
    private val scope: CoroutineScope,
    private val getCurrTime: GetCurrentTimeUseCase
) : GetDatabaseUseCase {
    private lateinit var db: AppDatabase
    private val eventDao by lazy { db.eventDao() }
    private val sportDao by lazy { db.sportDao() }
    private val teamDao by lazy { db.teamDao() }

    override fun invoke(): AppDatabase {
        db = Room.databaseBuilder(ctx, AppDatabase::class.java, DB_NAME)
            .createFromAsset("db/app.db")
            .build()
        scope.launch {
            importData()
        }
        return db
    }

    private suspend fun importData() = withContext(Dispatchers.IO) {
        val sports = listOf(
            Sport(1, "NBA"),
            Sport(2, "Soccer")
        )
        sportDao.insertAll(*sports.toTypedArray())

        val teams = listOf(
            Team(1, "Chicago Bulls", false, 1),
            Team(2, "Orlando Magic", false, 1),
            Team(3, "NY Knicks", false, 1),
            Team(4, "LA Lakers", false, 1),
            Team(5, "Boston Celtics", false, 1),
            Team(6, "Brooklyn Nets", false, 1),
            Team(7, "FC Barcelona", false, 2),
            Team(8, "Real Madrid", false, 2),
            Team(9, "Chelsea", false, 2),
            Team(10, "Arsenal", false, 2)
        )
        teamDao.insertAll(*teams.toTypedArray())

        val eventDate = getCurrTime().plus(1.hours)
        val events = listOf(
            Event(1, "Chicago Bulls @ Orlando Magic", 1, 2, eventDate, false),
            Event(2, "Boston Celtics @ Chicago Bulls", 5, 1, eventDate, false),
            Event(3, "LA Lakers @ Chicago Bulls", 4, 1, eventDate, false),
            Event(4, "Chicago Bulls @ NY Knicks", 1, 3, eventDate, false),
            Event(5, "Brooklyn Nets @ Chicago Bulls", 6, 1, eventDate, false),

            Event(6, "Orlando Magic @ LA Lakers", 2, 4, eventDate, false),
            Event(7, "LA Lakers @ Boston Celtics", 4, 5, eventDate, false),
            Event(8, "Chicago Bulls @ LA Lakers", 1, 4, eventDate, false)
        )
        eventDao.insertAll(*events.toTypedArray())
    }
}