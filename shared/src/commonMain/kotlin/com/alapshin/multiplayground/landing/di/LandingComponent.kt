package com.alapshin.multiplayground.landing.di

import com.alapshin.multiplayground.landing.domain.LandingBloc
import com.alapshin.multiplayground.root.domain.BlocFactory
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides

@Component
interface LandingComponent {
    @IntoMap
    @Provides
    fun provideLandingBlocFactory(
        navigation: StackNavigation<Config>,
    ): Pair<Config, BlocFactory> {
        return Config.Landing to { componentContext: ComponentContext ->
            LandingBloc(
                navigation = navigation,
                componentContext = componentContext,
            )
        }
    }
}
