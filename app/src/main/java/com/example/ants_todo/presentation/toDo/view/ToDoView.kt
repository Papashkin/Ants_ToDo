package com.example.ants_todo.presentation.toDo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.presentation.toDo.viewModel.ToDoViewModel
import kotlinx.android.synthetic.main.todo_fragment.*

class ToDoView : Fragment() {

    private lateinit var viewModel: ToDoViewModel
//    private var toDoModelFactory: ToDoModelFactory? = null

    fun newInstance(list: ListModel): ToDoView {
        val fragment = ToDoView()
        val args = Bundle()
        fragment.listId = list.id
        fragment.listName = list.name
        fragment.arguments = args
        return fragment
    }

    private var listId: Int = -1
    private var listName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.todo_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toDosToolbar.title = listName.toUpperCase()
        viewModel.setList(listId)

    }
}