package ru.nikitaboiko.testapproom.model

import io.reactivex.Single

class CarCache(private val database: Database) {

    fun getCars(): Single<List<Car>> {
        return Single.create {
            val cars = database.getCarDao().getAll() as ArrayList
            it.onSuccess(cars)
        }
    }

    fun getListWithRequest(request: String): Single<List<Car>> {
        return Single.create {
            val cars = database.getCarDao().findBy(request) as ArrayList
            it.onSuccess(cars)
        }
    }

    fun addNewValues(): Single<List<Car>> {
        return Single.create {
            val cars = arrayListOf(
                Car("a123aa777", "Nissan", "X-Trail", 8, "Gold"),
                Car("a222aa78", "Citroen", "c4", 2, "Black"),
                Car("a333aa777", "Audi", "Q7", 2, "White"),
                Car("c321ca777", "Audi", "A3", 12, "White"),
                Car("a124cc78", "BMW", "X5", 7, "Blue")
            )
            database.getCarDao().insert(cars)
            it.onSuccess(cars)
        }
    }

}