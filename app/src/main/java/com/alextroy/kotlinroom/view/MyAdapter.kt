package com.alextroy.kotlinroom.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alextroy.kotlinroom.R
import com.alextroy.kotlinroom.data.Person
import kotlinx.android.synthetic.main.person_list_item.view.*

class MyAdapter(private var items: List<Person>, val context: Context, private val listener: (Person) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.person_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list: List<Person>) {
        items = list
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val nameTV = view.name
    private val surNameTV = view.surname
    private val deleteBtn = view.delete

    fun bind(person: Person, listener: (Person) -> Unit) = with(itemView) {
        nameTV.text = person.firstName
        surNameTV.text = person.lastName
        deleteBtn.setOnClickListener { listener(person) }
    }
}