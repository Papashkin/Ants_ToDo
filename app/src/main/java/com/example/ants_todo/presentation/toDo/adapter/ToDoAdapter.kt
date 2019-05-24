package com.example.ants_todo.presentation.toDo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ToDoModel
import kotlinx.android.synthetic.main.todo_list_view.view.*

class ToDoAdapter(
    private val onItemClicked: (id: Int) -> Unit,
    private val onItemDeleted: (name: String, id: Int) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var toDoList = arrayListOf<ToDoModel>()

    fun setList(items: ArrayList<ToDoModel>) {
        toDoList.clear()
        toDoList.addAll(items.sortedBy { it.isChecked })
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


    inner class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: ToDoModel) {
            itemView.todoName.text = item.name
            setLayoutParams(item.isChecked)

            itemView.layoutToDo.setOnClickListener {
                onItemClicked.invoke(item.id)
            }
        }

        private fun setLayoutParams(isChecked: Boolean) {
            if (isChecked) {
                itemView.layoutToDo.background = itemView.resources.getDrawable(R.color.color_grey_400, null)
                itemView.todoName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                itemView.layoutToDo.alpha = 0.8f
            } else {
                itemView.layoutToDo.background = itemView.resources.getDrawable(R.color.color_white, null)
                itemView.todoName.paintFlags = Paint.ANTI_ALIAS_FLAG
                itemView.layoutToDo.alpha = 1f
            }
        }
    }
}