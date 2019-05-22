package com.example.ants_todo.di.modules

import androidx.room.Room
import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.db.lists.ListsDatabase
import com.example.ants_todo.data.db.toDo.ToDoDao
import com.example.ants_todo.data.db.toDo.ToDosDatabase
import com.example.ants_todo.data.repositories.ListsRepository
import com.example.ants_todo.data.repositories.ToDoRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val appModule = Kodein.Module("app") {
    // таблица со списками
    bind<ListsDatabase>() with singleton {
        Room.databaseBuilder(instance(), ListsDatabase::class.java, "lists")
            .allowMainThreadQueries()
            .build()
    }
    bind<ListsDao>() with singleton { instance<ListsDatabase>().listDao() }
    bind<ListsRepository>() with singleton { ListsRepository(instance()) }

    // таблица с элементами списков
    bind<ToDosDatabase>() with singleton {
        Room.databaseBuilder(instance(), ToDosDatabase::class.java, "toDos")
            .allowMainThreadQueries()
            .build()
    }
    bind<ToDoDao>() with singleton { instance<ToDosDatabase>().todoDao() }
    bind<ToDoRepository>() with singleton { ToDoRepository(instance()) }
}