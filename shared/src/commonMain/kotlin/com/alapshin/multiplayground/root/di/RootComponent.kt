package com.alapshin.multiplayground.root.di

import com.alapshin.multiplayground.di.DispatcherComponent
import com.alapshin.multiplayground.network.di.NetworkComponent
import com.alapshin.multiplayground.root.domain.RootBloc
import com.alapshin.multiplayground.root.domain.RootBlocImpl
import com.alapshin.multiplayground.users.di.UserComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class RootComponent(
    private val componentContext: ComponentContext,
) : DispatcherComponent, NetworkComponent, UserComponent {
    abstract val rootBloc: RootBloc

    @Provides
    fun rootBloc(bloc: RootBlocImpl): RootBloc = bloc

    @Provides
    fun storeFactory(): StoreFactory = DefaultStoreFactory()

    @Provides
    fun componentContext(): ComponentContext = componentContext
}
