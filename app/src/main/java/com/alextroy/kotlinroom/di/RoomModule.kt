package com.alextroy.kotlinroom.di

import android.arch.persistence.room.Room
import android.content.Context
import com.alextroy.kotlinroom.data.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule(private val context: Context) {

    @Provides fun providesAppContext() = context

    @Provides
    fun providesRoomDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "person").build()

    @Provides
    fun providesProductDao(database: AppDatabase) = database.personDao()

}