package com.example.ants_todo.presentation.toDo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ants_todo.R
import com.example.ants_todo.data.models.ListModel
import kotlinx.android.synthetic.main.todo_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class ToDoView : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()

    private lateinit var viewModel: ToDoViewModel
    private lateinit var toDoModelFactory: ToDoModelFactory

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

        toDoModelFactory = ToDoModelFactory(listId, kodein)
        viewModel = ViewModelProviders.of(this, toDoModelFactory).get(ToDoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.todo_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toDosToolbar.title = listName.toUpperCase()

        viewModel.toDos.observe(this, Observer {

        })

    }
}