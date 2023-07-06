package com.alapshin.multiplayground.login.di

import com.alapshin.multiplayground.login.domain.LoginBloc
import com.alapshin.multiplayground.login.domain.LoginPresenter
import com.alapshin.multiplayground.registration.domain.RegistrationBloc
import com.alapshin.multiplayground.registration.domain.RegistrationPresenter
import com.alapshin.multiplayground.registration.domain.RegistrationPresenterImpl
import com.alapshin.multiplayground.root.domain.BlocFactory
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides

@Component
interface RegistrationComponent {
    @Provides
    fun provideRegistrationPresenter(
        presenter: RegistrationPresenterImpl
    ): RegistrationPresenter = presenter

    @IntoMap
    @Provides
    fun provideRegistrationBlocFactory(
        presenter: RegistrationPresenter,
        navigation: StackNavigation<Config>,
    ): Pair<Config, BlocFactory> {
        return Config.Registration to { componentContext: ComponentContext ->
            RegistrationBloc(
                presenter = presenter,
                navigation = navigation,
                componentContext = componentContext
            )
        }
    }
}
