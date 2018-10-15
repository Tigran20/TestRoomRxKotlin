package com.alextroy.kotlinroom.di

import com.alextroy.kotlinroom.data.PersonDao
import com.alextroy.kotlinroom.data.AppDatabase
import com.alextroy.kotlinroom.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface AppComponent {
    fun inject(app: MainActivity)

    fun personDao(): PersonDao
    fun personDatabase(): AppDatabase
}