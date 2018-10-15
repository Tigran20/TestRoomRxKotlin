package com.alextroy.kotlinroom.bd

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}