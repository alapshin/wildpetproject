package com.alapshin.multiplayground.login.di

import com.alapshin.multiplayground.login.data.LoginManager
import com.alapshin.multiplayground.login.data.LoginManagerImpl
import com.alapshin.multiplayground.login.data.LoginService
import com.alapshin.multiplayground.login.domain.LoginBlocImpl
import com.alapshin.multiplayground.login.domain.LoginStoreFactory
import com.alapshin.multiplayground.root.domain.BlocFactory
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import de.jensklingenberg.ktorfit.Ktorfit
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides

@Component
interface LoginComponent {
    @Provides
    fun provideLoginService(ktorfit: Ktorfit): LoginService = ktorfit.create<LoginService>()

    @Provides
    fun provideUserRepository(manager: LoginManagerImpl): LoginManager = manager

    @IntoMap
    @Provides
    fun provideLoginBlocFactory(storeFactory: LoginStoreFactory): Pair<Config, BlocFactory> {
        return Config.Login to { componentContext: ComponentContext ->
            LoginBlocImpl(storeFactory, componentContext)
        }
    }
}
