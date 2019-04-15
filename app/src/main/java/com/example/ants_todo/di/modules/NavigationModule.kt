package com.example.ants_todo.di.modules

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

val navigationModule = Kodein.Module("navigation") {
    bind<Cicerone<Router>>() with singleton { Cicerone.create() }
    bind<Router>() with singleton { instance<Cicerone<Router>>().router }
    bind<NavigatorHolder>() with singleton { instance<Cicerone<Router>>().navigatorHolder }
}
