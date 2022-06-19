package loc.example.dev.sportstickerapp061922.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

interface GetCurrentTimeUseCase {
    operator fun invoke(): Instant
}

internal class GetCurrentTimeUseCaseImpl : GetCurrentTimeUseCase {
    override fun invoke() = Clock.System.now()
}