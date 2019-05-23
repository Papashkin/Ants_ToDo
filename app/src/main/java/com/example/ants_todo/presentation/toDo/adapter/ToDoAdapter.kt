package com.example.ants_todo.presentation.toDo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ToDoModel
import kotlinx.android.synthetic.main.todo_list_view.view.*

class ToDoAdapter(
    private val onItemClicked: (id: Int) -> Unit,
    private val onItemDeleted: (name: String, id: Int) -> Unit,
    private val onItemEdit: (id: Int) -> Unit
): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var toDoList = arrayListOf<ToDoModel>()

    fun setList(items: ArrayList<ToDoModel>) {
        toDoList.clear()
        toDoList.addAll(items)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        val selectedItem = toDoList[position]
        onItemDeleted.invoke(selectedItem.name, selectedItem.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_view, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int = toDoList.size

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(toDoList[position])
    }


    inner class ToDoViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(item: ToDoModel) {

            itemView.todoName.text = item.name
            itemView.cb_isChecked.isChecked = item.isChecked

            itemView.layoutToDo.setOnClickListener {
                onItemClicked.invoke(item.id)
            }

            itemView.cb_isChecked.setOnClickListener {
                onItemClicked.invoke(item.id)
            }

        }
    }
}