package loc.example.dev.sportstickerapp061922

import android.app.Application
import loc.example.dev.sportstickerapp061922.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        injectDependency()
    }

    private fun injectDependency() {
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}