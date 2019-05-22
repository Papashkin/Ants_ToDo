package com.example.ants_todo.di.modules

import androidx.room.Room
import com.example.ants_todo.data.db.lists.ListsDao
import com.example.ants_todo.data.db.lists.ListsDatabase
import com.example.ants_todo.data.repositories.ListsRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val appModule = Kodein.Module("app") {

    bind<ListsDatabase>() with singleton {
        Room
            .databaseBuilder(
                instance(),
                ListsDatabase::class.java,
                "lists"
            )
            .allowMainThreadQueries()
            .build()
    }

    bind<ListsDao>() with singleton { instance<ListsDatabase>().todoDao() }
    bind<ListsRepository>() with singleton { ListsRepository(instance()) }

}