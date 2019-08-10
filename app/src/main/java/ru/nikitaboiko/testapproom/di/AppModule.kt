package ru.nikitaboiko.testapproom.di

import dagger.Module
import dagger.Provides
import ru.nikitaboiko.testapproom.App
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun app(): App {
        return app
    }

}