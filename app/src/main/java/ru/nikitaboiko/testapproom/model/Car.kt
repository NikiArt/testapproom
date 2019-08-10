package ru.nikitaboiko.testapproom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Car(
    @PrimaryKey val carNumber: String,
    val model: String,
    val submodel: String,
    val age: Int,
    val color: String
) {
}