package com.example.ants_todo.presentation.lists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel

class ListsAdapter(
    private val onItemClick: (list: ListModel) -> Unit,
    private val onItemDelete: (id: Int, name: String) -> Unit
) : ListAdapter<ListModel, ListsAdapter.TodoViewHolder>(ListsItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lists_view, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: TodoViewHolder, pos: Int) {
        val item = getItem(pos)
        viewHolder.bind(item)
    }

    fun deleteItem(position: Int) {
        val item = getItem(position)
        onItemDelete.invoke(item.id, item.name)
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textVew = itemView.findViewById<TextView>(R.id.listName)

        fun bind(item: ListModel) {
            textVew.text = item.name

            textVew.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }

}