package com.example.ants_todo.util.navigation

import androidx.fragment.app.Fragment
import com.example.ants_todo.data.models.ListModel
import com.example.ants_todo.presentation.lists.view.ListsView
import com.example.ants_todo.presentation.toDo.view.ToDoView
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ListsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = ListsView()
        override fun getScreenKey(): String = "lists view screen"
    }

    class ToDosScreen(private val list: ListModel) : SupportAppScreen() {
        override fun getFragment(): Fragment = ToDoView().newInstance(list)
        override fun getScreenKey(): String = "toDos view screen"
    }
}