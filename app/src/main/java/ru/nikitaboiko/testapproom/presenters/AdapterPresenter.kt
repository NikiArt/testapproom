package ru.nikitaboiko.testapproom.presenters

import ru.nikitaboiko.testapproom.model.Car
import ru.nikitaboiko.testapproom.ui.Adapter

class AdapterPresenter {
    var cars = ArrayList<Car>()

    fun getCount(): Int {
        return cars.size
    }

    fun bind(holder: Adapter.ViewHolder) {
        val car = cars[holder.getCurrentPos()]
        holder.setValuesOnView(
            car.model,
            car.submodel,
            car.carNumber,
            car.age.toString(),
            car.color
        )
    }
}