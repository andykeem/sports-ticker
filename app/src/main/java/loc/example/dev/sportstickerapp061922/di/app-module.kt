package loc.example.dev.sportstickerapp061922.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import loc.example.dev.sportstickerapp061922.db.dao.EventDao
import loc.example.dev.sportstickerapp061922.db.dao.SportDao
import loc.example.dev.sportstickerapp061922.db.dao.TeamDao
import loc.example.dev.sportstickerapp061922.repo.EventRepo
import loc.example.dev.sportstickerapp061922.repo.EventRepository
import loc.example.dev.sportstickerapp061922.repo.TeamRepo
import loc.example.dev.sportstickerapp061922.repo.TeamRepository
import loc.example.dev.sportstickerapp061922.usecase.GetCurrentTimeUseCase
import loc.example.dev.sportstickerapp061922.usecase.GetCurrentTimeUseCaseImpl
import loc.example.dev.sportstickerapp061922.usecase.GetDatabaseUseCase
import loc.example.dev.sportstickerapp061922.usecase.GetDatabaseUseCaseImpl
import loc.example.dev.sportstickerapp061922.viewmodel.TeamViewModel
import loc.example.dev.sportstickerapp061922.viewmodel.TickerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GetCurrentTimeUseCase> { GetCurrentTimeUseCaseImpl() }
    single<CoroutineScope> { CoroutineScope(SupervisorJob()) }
    single<GetDatabaseUseCase> { GetDatabaseUseCaseImpl(androidContext(), get(), get()) }
    single<EventDao> { GetDatabaseUseCaseImpl(androidContext(), get(), get()).invoke().eventDao() }
    single<SportDao> { GetDatabaseUseCaseImpl(androidContext(), get(), get()).invoke().sportDao() }
    single<TeamDao> { GetDatabaseUseCaseImpl(androidContext(), get(), get()).invoke().teamDao() }
    single<TeamRepo> { TeamRepository(get()) }
    single<EventRepo> { EventRepository(get()) }
    viewModel { TickerViewModel(get(), get()) }
    viewModel { TeamViewModel(get()) }
}