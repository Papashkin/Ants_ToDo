package com.example.ants_todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.erased.instance
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val router: Router by ToDoApplication().kodein.instance()
    private val navHolder: NavigatorHolder by ToDoApplication().kodein.instance()

    private val navigator by lazy { SupportAppNavigator(this, R.id.fragmentLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        router.newRootScreen()
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
