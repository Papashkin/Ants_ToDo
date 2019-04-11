package com.example.ants_todo

import android.app.Application
import android.content.Context
import com.example.ants_todo.di.modules.appModule
import com.example.ants_todo.di.modules.navigationModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

class ToDoApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        bind<Context>() with singleton { applicationContext }
        import(navigationModule)
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
    }

}