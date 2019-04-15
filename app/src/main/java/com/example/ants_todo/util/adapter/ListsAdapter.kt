package com.example.ants_todo.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R
import com.example.ants_todo.data.ToDoList

class ListsAdapter(
    private val onItemClick: (id: Int) -> Unit,
    private val onItemDelete: (id: Int) -> Unit
) : ListAdapter<ToDoList, ListsAdapter.TodoViewHolder>(TodoItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_view, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: TodoViewHolder, pos: Int) {
        val item = getItem(pos)
        viewHolder.bind(item)
    }

    fun deleteItem(position: Int) {
        val item = getItem(position)
        onItemDelete.invoke(item.id)
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textVew = itemView.findViewById<TextView>(R.id.listName)

        fun bind(item: ToDoList) {
            textVew.text = item.name

            textVew.setOnClickListener {
                onItemClick.invoke(item.id)
            }
        }
    }

}

private class TodoItemCallback : DiffUtil.ItemCallback<ToDoList>() {
    override fun areItemsTheSame(item1: ToDoList, item2: ToDoList): Boolean {
        return item1.id == item2.id
    }

    override fun areContentsTheSame(item1: ToDoList, item2: ToDoList): Boolean {
        return item1.name == item2.name
    }
}