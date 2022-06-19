package loc.example.dev.sportstickerapp061922.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import loc.example.dev.sportstickerapp061922.repo.TeamRepo
import loc.example.dev.sportstickerapp061922.repo.TeamRepository
import loc.example.dev.sportstickerapp061922.usecase.GetDatabaseUseCase
import loc.example.dev.sportstickerapp061922.usecase.GetDatabaseUseCaseImpl
import loc.example.dev.sportstickerapp061922.viewmodel.TeamViewModel
import loc.example.dev.sportstickerapp061922.viewmodel.TickerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> { CoroutineScope(SupervisorJob()) }
    single<GetDatabaseUseCase> { GetDatabaseUseCaseImpl(androidContext(), get()) }
    single<TeamRepo> { TeamRepository(get()) }
    viewModel { TickerViewModel(get()) }
    viewModel { TeamViewModel(get()) }
}