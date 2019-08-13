package ru.nikitaboiko.testapproom.model

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nikitaboiko.testapproom.model.dao.CarDao

@Database(entities = [Car::class], version = 1)
abstract class Database : RoomDatabase() {
    companion object {
        const val DB_NAME = "carbase.db"
    }

    abstract fun getCarDao(): CarDao

}