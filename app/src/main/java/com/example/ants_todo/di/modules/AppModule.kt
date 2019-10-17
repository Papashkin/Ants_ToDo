package com.example.ants_todo.di.modules

import androidx.room.Room
import com.example.ants_todo.data.db.AntsToDoDatabase
import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.db.toDo.ToDoDao
import com.example.ants_todo.data.db.lists.ListsRepository
import com.example.ants_todo.data.db.toDo.ToDoRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val appModule = Kodein.Module("app") {
    /**
     * applications's DataBase
     */
    bind<AntsToDoDatabase>() with singleton {
        Room.databaseBuilder(instance(), AntsToDoDatabase::class.java, "toDoApp")
            .build()
    }

    /**
     * Table with list's names
     */
    bind<ListsDao>() with singleton { instance<AntsToDoDatabase>().listDao() }
    bind<ListsRepository>() with singleton { ListsRepository(instance()) }

    /**
     * Table with items's names
     */
    bind<ToDoDao>() with singleton { instance<AntsToDoDatabase>().toDoDao() }
    bind<ToDoRepository>() with singleton { ToDoRepository(instance()) }
}