package loc.example.dev.sportstickerapp061922.usecase

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import loc.example.dev.sportstickerapp061922.constant.DB_NAME
import loc.example.dev.sportstickerapp061922.db.AppDatabase
import loc.example.dev.sportstickerapp061922.db.entity.Sport
import loc.example.dev.sportstickerapp061922.db.entity.Team

interface GetDatabaseUseCase {
    operator fun invoke(): AppDatabase
}

internal class GetDatabaseUseCaseImpl(
    private val ctx: Context,
    private val scope: CoroutineScope
) : GetDatabaseUseCase {
    override fun invoke(): AppDatabase {
        val db = Room.databaseBuilder(ctx, AppDatabase::class.java, DB_NAME)
            .createFromAsset("db/app.db")
            .build()
        scope.launch {
            importData(db)
        }
        return db
    }

    private suspend fun importData(db: AppDatabase) = withContext(Dispatchers.IO) {
        val sportDao = db.sportDao()
        val teamDao = db.teamDao()

        val sports = listOf(
            Sport(1, "NBA"),
            Sport(2, "Soccer")
        )
        sportDao.insertAll(*sports.toTypedArray())

        val teams = listOf(
            Team(1, "Chicago Bulls", false, 1),
            Team(2, "Orlando Magic", false, 1),
            Team(3, "NY Nicks'", false, 1),
            Team(4, "FC Barcelona", false, 2),
            Team(5, "Real Madrid", false, 2),
            Team(6, "Chelsea", false, 2),
            Team(7, "Arsenal", false, 2)
        )
        teamDao.insertAll(*teams.toTypedArray())
    }
}