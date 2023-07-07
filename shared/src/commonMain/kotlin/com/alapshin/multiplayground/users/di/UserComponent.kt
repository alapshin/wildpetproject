package com.alapshin.multiplayground.users.di

import com.alapshin.multiplayground.root.domain.BlocFactory
import com.alapshin.multiplayground.root.domain.Config
import com.alapshin.multiplayground.users.data.UserRepository
import com.alapshin.multiplayground.users.data.UserRepositoryImpl
import com.alapshin.multiplayground.users.data.UserService
import com.alapshin.multiplayground.users.domain.UserListBloc
import com.alapshin.multiplayground.users.domain.UserListPresenter
import com.alapshin.multiplayground.users.domain.UserListPresenterImpl
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import de.jensklingenberg.ktorfit.Ktorfit
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides

@Component
interface UserComponent {
    @Provides
    fun provideUserService(ktorfit: Ktorfit): UserService = ktorfit.create<UserService>()

    @Provides
    fun provideUserRepository(repository: UserRepositoryImpl): UserRepository = repository

    @Provides
    fun provideUserListPresenter(presenter: UserListPresenterImpl): UserListPresenter = presenter

    @IntoMap
    @Provides
    fun provideLoginBlocFactory(
        presenter: UserListPresenter,
        navigation: StackNavigation<Config>,
    ): Pair<Config, BlocFactory> {
        return Config.UserList to { componentContext: ComponentContext ->
            UserListBloc(
                presenter = presenter,
                componentContext = componentContext,
            )
        }
    }
}
