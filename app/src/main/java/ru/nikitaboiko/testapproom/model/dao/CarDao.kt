package ru.nikitaboiko.testapproom.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nikitaboiko.testapproom.model.Car

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(car: Car)

    @Query(
        "SELECT * FROM car WHERE " +
                "carNumber LIKE '%' || :searchValue || '%'" +
                " OR model LIKE '%' || :searchValue || '%'" +
                " OR submodel LIKE '%' || :searchValue || '%'" +
                " OR CAST(age AS TEXT) LIKE '%' || :searchValue || '%'" +
                " OR color LIKE '%' || :searchValue || '%'"
    )
    fun findBy(searchValue: String = ""): List<Car>

    @Query("SELECT * FROM car")
    fun getAll(): List<Car>
}