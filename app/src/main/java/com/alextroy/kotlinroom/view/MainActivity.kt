package com.alextroy.kotlinroom.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.alextroy.kotlinroom.R
import com.alextroy.kotlinroom.data.Person
import com.alextroy.kotlinroom.data.PersonDao
import com.alextroy.kotlinroom.di.DaggerAppComponent
import com.alextroy.kotlinroom.di.RoomModule
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.person_list.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val name = "Alex"
    private val surName = "Troy"

    private lateinit var person: List<Person>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: MyAdapter
    private lateinit var fab: FloatingActionButton

    @Inject
    lateinit var personDao: PersonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_list)

        init()
        itemAdd()
    }

    private fun init() {
        DaggerAppComponent
            .builder()
            .roomModule(RoomModule(this))
            .build()
            .inject(this)

        fab = findViewById(R.id.fab)
        linearLayoutManager = LinearLayoutManager(this)
        person = ArrayList()
        adapter = MyAdapter(person, this)

        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = adapter
        registerAllPersonListener()
    }

    private fun itemAdd() {
        fab.setOnClickListener {
            addPerson(name, surName)
            adapter.setData(person)
            registerAllPersonListener()
        }
    }

//    private fun addPerson(firstName: String, lastName: String) {
//        val person = Person(0, firstName, lastName)
//        Single.fromCallable { RoomModule.database!!.personDao().insert(person) }
//            .subscribeOn(Schedulers.io())
//            .subscribe()
//    }

//    @SuppressLint("CheckResult")
//    private fun registerAllPersonListener() {
//        RoomModule.database!!.personDao().getAllPeople()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { adapter.setData(it) }
//    }

    private fun addPerson(firstName: String, lastName: String) {
        val person = Person(0, firstName, lastName)
        Single.fromCallable { personDao.insert(person) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    @SuppressLint("CheckResult")
    private fun registerAllPersonListener() {
        personDao.getAllPeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { adapter.setData(it) }
    }


}
