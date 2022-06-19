package loc.example.dev.sportstickerapp061922.service

import loc.example.dev.sportstickerapp061922.db.entity.Sport
import loc.example.dev.sportstickerapp061922.usecase.GetDatabaseUseCase

interface DataImportService {
    operator fun invoke()
}

internal class DataImportServiceImpl(
    private val getDatabase: GetDatabaseUseCase
) : DataImportService {
    private val db = getDatabase()

    override fun invoke() {
        val sports = listOf("NBA", "Soccer").mapIndexed { index, s -> Sport(index, s) }
        db.sportDao().insertAll(*sports.toTypedArray())
    }
}