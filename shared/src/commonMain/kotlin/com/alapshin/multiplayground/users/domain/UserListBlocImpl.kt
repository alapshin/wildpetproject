package com.alapshin.multiplayground.users.domain

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.flow.StateFlow
import me.tatarka.inject.annotations.Inject

@Inject
internal class UserListBlocImpl(
    private val storeFactory: UserListStoreFactory,
    private val componentContext: ComponentContext,
) : UserListBloc, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore {
        storeFactory.create()
    }

    override val state: StateFlow<UserListMvi.State> = store.stateFlow
}
