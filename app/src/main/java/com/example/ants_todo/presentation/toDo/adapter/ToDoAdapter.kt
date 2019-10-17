package com.example.ants_todo.presentation.toDo.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.util.adapter.AntsAdapter
import com.example.ants_todo.util.adapter.AntsViewHolder
import kotlinx.android.synthetic.main.card_todo_list.view.*

class ToDoAdapter(
    private val onItemClicked: (id: Int) -> Unit,
    private val onItemDeleted: (name: String, id: Int) -> Unit
) : AntsAdapter<ToDoAdapter.ToDoViewHolder, ToDoModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder =
        ToDoViewHolder(inflate(parent, R.layout.card_todo_list))

    override fun deleteItem(position: Int) {
        val selectedItem = getList()[position]
        onItemDeleted.invoke(selectedItem.name, selectedItem.id)
    }

    inner class ToDoViewHolder(view: View) : AntsViewHolder<ToDoModel>(view) {
        override fun bind(item: ToDoModel, position: Int) {
           itemView.tvItemName.text = item.name
            if (item.isChecked) {
                itemView.tvItemName.alpha = 0.7f
                itemView.tvItemName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_grey_700))
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.color_grey_400))
            } else {
                itemView.tvItemName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_black))
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.color_white))
                itemView.tvItemName.alpha = 1f
            }
            itemView.setOnClickListener {
                onItemClicked.invoke(item.id)
            }
        }
    }
}