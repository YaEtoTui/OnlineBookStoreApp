package sazhin.onlinebookstoreapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sazhin.onlinebookstoreapp.di.dbModule
import sazhin.onlinebookstoreapp.di.networkModule
import sazhin.onlinebookstoreapp.di.rootModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule, dbModule)
        }
    }
}