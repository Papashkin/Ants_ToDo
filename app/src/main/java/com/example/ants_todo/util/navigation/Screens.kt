package com.example.ants_todo.util.navigation

import androidx.fragment.app.Fragment
import com.example.ants_todo.presentation.view.ListsView
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ListsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = ListsView()
        override fun getScreenKey(): String = "lists view screen"
    }

}