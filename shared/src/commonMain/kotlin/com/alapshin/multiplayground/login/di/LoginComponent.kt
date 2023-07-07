package com.alapshin.multiplayground.login.di

import com.alapshin.multiplayground.auth.AuthService
import com.alapshin.multiplayground.login.data.LoginManager
import com.alapshin.multiplayground.login.data.LoginManagerImpl
import com.alapshin.multiplayground.login.domain.LoginBloc
import com.alapshin.multiplayground.login.domain.LoginPresenter
import com.alapshin.multiplayground.login.domain.LoginPresenterImpl
import com.alapshin.multiplayground.root.domain.BlocFactory
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import de.jensklingenberg.ktorfit.Ktorfit
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides

@Component
interface LoginComponent {
    @Provides
    fun provideAuthService(ktorfit: Ktorfit): AuthService = ktorfit.create()

    @Provides
    fun provideLoginManager(manager: LoginManagerImpl): LoginManager = manager

    @Provides
    fun provideLoginPresenter(presenter: LoginPresenterImpl): LoginPresenter = presenter

    @IntoMap
    @Provides
    fun provideLoginBlocFactory(
        presenter: LoginPresenter,
        navigation: StackNavigation<Config>,
    ): Pair<Config, BlocFactory> {
        return Config.Login to { componentContext: ComponentContext ->
            LoginBloc(
                presenter = presenter,
                navigation = navigation,
                componentContext = componentContext,
            )
        }
    }
}
