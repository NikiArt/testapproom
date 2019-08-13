package ru.nikitaboiko.testapproom.view

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun initialize()

    fun updateList()
}