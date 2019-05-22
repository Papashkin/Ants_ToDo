package com.example.ants_todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ants_todo.util.navigation.Screens
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.erased.instance
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by kodein()

    private val cicerone: Cicerone<Router> by instance()
    private val navHolder: NavigatorHolder by lazy { cicerone.navigatorHolder }
    private val router: Router by instance()
    private val navigator by lazy { SupportAppNavigator(this, R.id.fragmentLayout) }

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