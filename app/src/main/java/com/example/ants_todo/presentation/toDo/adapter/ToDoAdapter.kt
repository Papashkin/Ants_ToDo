package com.example.ants_todo.presentation.toDo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ToDoModel
import com.example.ants_todo.databinding.TodoListViewBinding

class ToDoAdapter(
    private val onItemClicked: (id: Int) -> Unit,
    private val onItemDeleted: (name: String, id: Int) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private lateinit var diffCallback: ToDoDiffCallback
    private lateinit var diffResult: DiffUtil.DiffResult

    private var toDoList = arrayListOf<ToDoModel>()
    private val listener = object : ToDoClickListener {
        override fun onClick(id: Int) {
            onItemClicked.invoke(id)
        }
    }

    fun setList(items: ArrayList<ToDoModel>) {
        val sortedItems = items.sortedBy { it.isChecked }
        diffCallback = ToDoDiffCallback(
            oldList = toDoList,
            newList = sortedItems
        )
        diffResult = DiffUtil.calculateDiff(diffCallback)
        toDoList.clear()
        toDoList.addAll(sortedItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun deleteItem(position: Int) {
        val selectedItem = toDoList[position]
        onItemDeleted.invoke(selectedItem.name, selectedItem.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TodoListViewBinding>(inflater, R.layout.todo_list_view, parent, false)
        return ToDoViewHolder(binding.root)
    }

    override fun getItemCount(): Int = toDoList.size

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.getBinding().todo = toDoList[position]
        holder.getBinding().listener = listener
    }

    inner class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: TodoListViewBinding = DataBindingUtil.bind(view)!!

        fun getBinding(): TodoListViewBinding = binding
    }
}