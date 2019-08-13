package ru.nikitaboiko.testapproom.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nikitaboiko.testapproom.App
import ru.nikitaboiko.testapproom.model.CarCache
import ru.nikitaboiko.testapproom.model.Database
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class CacheModule {
    @Singleton
    @Provides
    fun getDatabase(app: App): Database {
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun getCache(database: Database): CarCache {
        return CarCache(database)
    }
}