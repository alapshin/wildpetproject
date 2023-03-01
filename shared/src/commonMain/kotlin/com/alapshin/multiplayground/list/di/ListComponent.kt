package com.alapshin.multiplayground.list.di

import com.alapshin.multiplayground.list.domain.ListBlocImpl
import com.alapshin.multiplayground.root.domain.BlocFactory
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides

@Component
interface ListComponent {
    @IntoMap
    @Provides
    fun mainBlocFactory(): Pair<Config, BlocFactory> {
        return Config.Main to { componentContext: ComponentContext -> ListBlocImpl(componentContext) }
    }
}