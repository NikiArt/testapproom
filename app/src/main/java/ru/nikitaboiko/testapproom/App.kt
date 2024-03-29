package ru.nikitaboiko.testapproom

import android.app.Application
import ru.nikitaboiko.testapproom.di.AppComponent
import ru.nikitaboiko.testapproom.di.AppModule
import ru.nikitaboiko.testapproom.di.DaggerAppComponent

class App : Application() {
    private lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App

        fun instance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }


}