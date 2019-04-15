package com.example.ants_todo.di.modules

import androidx.room.Room
import com.example.ants_todo.data.lists.ToDoListDao
import com.example.ants_todo.data.lists.ToDoListDatabase
import com.example.ants_todo.data.repositories.ToDoListRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val appModule = Kodein.Module("app") {

    bind<ToDoListDatabase>() with singleton {
        Room.databaseBuilder(
            instance(),
            ToDoListDatabase::class.java,
            "todo_lists"
        ).allowMainThreadQueries()
            .build()
    }

    bind<ToDoListDao>() with singleton { instance<ToDoListDatabase>().todoDao() }

    bind<ToDoListRepository>() with singleton { ToDoListRepository(instance()) }

}
