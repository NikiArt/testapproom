package ru.nikitaboiko.testapproom.di

import dagger.Component
import ru.nikitaboiko.testapproom.presenters.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, CacheModule::class])
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
}