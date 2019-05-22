package com.example.ants_todo

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.ants_todo.di.modules.appModule
import com.example.ants_todo.di.modules.navigationModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule

class ToDoApplication : Application(), KodeinAware {

    private var viewModelFactory: ViewModelProvider.AndroidViewModelFactory? = null

    override val kodein by Kodein.lazy {
        import(androidCoreModule(this@ToDoApplication))
        import(navigationModule)
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }

}