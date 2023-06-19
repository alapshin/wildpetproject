package com.alapshin.multiplayground.users.domain

import androidx.compose.runtime.Composable
import com.alapshin.multiplayground.compound.Bloc
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import kotlinx.coroutines.flow.Flow

class UserListBloc(
    private val presenter: UserListPresenter,
    private val navigation: StackNavigation<Config>,
    private val componentContext: ComponentContext,
) : Bloc<UserListMvi.Event, UserListMvi.State>(componentContext) {
    @Composable
    override fun state(events: Flow<UserListMvi.Event>): UserListMvi.State {
        return presenter.state(events)
    }
}
