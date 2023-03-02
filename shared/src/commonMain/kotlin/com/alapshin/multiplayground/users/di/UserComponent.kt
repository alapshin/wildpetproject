package com.alapshin.multiplayground.users.di

import com.alapshin.multiplayground.root.domain.BlocFactory
import com.alapshin.multiplayground.root.domain.Config
import com.alapshin.multiplayground.users.data.UserRepository
import com.alapshin.multiplayground.users.data.UserRepositoryImpl
import com.alapshin.multiplayground.users.data.UserService
import com.alapshin.multiplayground.users.domain.UserListBlocImpl
import com.alapshin.multiplayground.users.domain.UserListStoreFactory
import com.arkivanov.decompose.ComponentContext
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

    @IntoMap
    @Provides
    fun provideUserListBlocFactory(storeFactory: UserListStoreFactory): Pair<Config, BlocFactory> {
        return Config.UserList to { componentContext: ComponentContext ->
            UserListBlocImpl(storeFactory, componentContext)
        }
    }
}
