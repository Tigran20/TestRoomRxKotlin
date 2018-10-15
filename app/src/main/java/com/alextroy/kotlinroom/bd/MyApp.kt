package com.alextroy.kotlinroom.bd

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context

class MyApp : Application() {

    companion object {
        private const val DB_NAME = "person"

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "Sample.db").build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = Room.databaseBuilder(this, MyDatabase::class.java, DB_NAME)
            .build()
    }
}