package loc.example.dev.sportstickerapp061922.repo

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import loc.example.dev.sportstickerapp061922.db.dao.EventDao
import loc.example.dev.sportstickerapp061922.db.entity.Event
import kotlin.time.Duration.Companion.seconds

private const val TAG = "EventRepo"

interface EventRepo {
    fun getEvent(): Flow<Event>
}

@OptIn(ExperimentalCoroutinesApi::class)
internal class EventRepository(private val eventDao: EventDao) : EventRepo {
    private val intervalMs = 10.seconds.inWholeMilliseconds
    private var index = 0

    override fun getEvent(): Flow<Event> {
        return eventDao.getAll()
            .filter { it.size > 0 }
            .map { it.sortedBy { it.eventDate } }
            .flatMapLatest { events ->
                flow {
                    while (currentCoroutineContext().isActive) {
                        Log.d(TAG, "getEvent: index: $index, events.size: ${events.size}")
                        index %= events.size
                        Log.d(TAG, "getEvent: index: $index")
                        val event = events[index++]
                        emit(event)
                        delay(intervalMs)
                    }
                }
            }
    }
}