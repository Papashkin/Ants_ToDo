package com.example.ants_todo.presentation

import android.app.Application
import com.example.ants_todo.di.modules.appModule
import com.example.ants_todo.di.modules.navigationModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance

class ToDoApplication: Application(), KodeinAware {
    companion object {
        var INSTANCE: ToDoApplication? = null
        fun getKodein() = INSTANCE!!.kodein
    }

    override val kodein: Kodein by Kodein.lazy {
        bind<Application>() with instance(this@ToDoApplication)
        import(navigationModule)
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}