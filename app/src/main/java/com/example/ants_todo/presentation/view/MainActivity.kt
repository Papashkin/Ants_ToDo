package com.example.ants_todo.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ants_todo.R
import com.example.ants_todo.ToDoApplication
import com.example.ants_todo.util.navigation.Screens
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by lazy {
        (application as ToDoApplication).kodein
    }
    private val navHolder: NavigatorHolder by instance()
    private val router: Router by instance()
    private val navigator by lazy {
        SupportAppNavigator(this, R.id.fragmentLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.newRootScreen(Screens.ListsScreen())
    }

    override fun onResume() {
        super.onResume()
        navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navHolder.removeNavigator()
    }

    override fun onBackPressed() {
        router.exit()
    }
}