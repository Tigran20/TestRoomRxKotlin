package com.alextroy.kotlinroom.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun getAllPeople(): Flowable<List<Person>>

    @Insert
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)
}