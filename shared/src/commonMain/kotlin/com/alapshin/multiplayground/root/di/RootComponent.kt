package com.alapshin.multiplayground.root.di

import com.alapshin.multiplayground.list.di.ListComponent
import com.alapshin.multiplayground.root.domain.RootBloc
import com.alapshin.multiplayground.root.domain.RootBlocImpl
import com.arkivanov.decompose.ComponentContext
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class RootComponent(
    private val componentContext: ComponentContext,
): ListComponent {
    abstract val rootBloc: RootBloc

    @Provides
    fun rootBloc(bloc: RootBlocImpl): RootBloc = bloc

    @Provides
    fun componentContext(): ComponentContext = componentContext
}