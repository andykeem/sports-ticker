package loc.example.dev.sportstickerapp061922.repo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import loc.example.dev.sportstickerapp061922.db.dao.EventDao
import loc.example.dev.sportstickerapp061922.db.entity.Event
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration.Companion.seconds

interface EventRepo {
    fun getEvent(): Flow<Event>
}

@OptIn(ExperimentalCoroutinesApi::class)
internal class EventRepository(private val eventDao: EventDao) : EventRepo {
    private val intervalMs = 10.seconds.inWholeMilliseconds
    private var index = 0

    override fun getEvent(): Flow<Event> {
        return eventDao.getAll().map { it.sortedBy { it.eventDate } }
            .flatMapLatest { events ->
                flow {
                    while (coroutineContext.isActive) {
                        index %= events.size
                        val event = events[index++]
                        emit(event)
                        delay(intervalMs)
                    }
                }
            }
    }
}