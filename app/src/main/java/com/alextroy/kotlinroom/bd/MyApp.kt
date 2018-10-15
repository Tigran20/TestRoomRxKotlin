package com.alextroy.kotlinroom.bd

import android.app.Application
import android.arch.persistence.room.Room

class MyApp : Application() {

    companion object {
        private const val DB_NAME = "person"
        var database: MyDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, MyDatabase::class.java, DB_NAME).build()
    }
}