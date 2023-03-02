package com.alapshin.multiplayground.users.domain

import com.alapshin.multiplayground.DispatcherProvider
import com.alapshin.multiplayground.users.data.User
import com.alapshin.multiplayground.users.data.UserRepository
import com.alapshin.multiplayground.users.domain.UserListMvi.Intent
import com.alapshin.multiplayground.users.domain.UserListMvi.State
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
class UserListStoreFactory(
    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
    private val dispatcherProvider: DispatcherProvider,
) {
    fun create(): UserListStore {
        return object :
            UserListStore,
            Store<Intent, State, Nothing> by storeFactory.create(
                name = UserListStore::class.simpleName,
                initialState = State(),
                reducer = ReducerImpl,
                executorFactory = ::ExecutorImpl,
                bootstrapper = SimpleBootstrapper(Action.Load)
            ) {
        }
    }

    private sealed interface Action {
        object Load : Action
    }

    private sealed interface Message {
        data class UserList(val users: List<User>) : Message
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                is Message.UserList -> copy(users = msg.users)
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Nothing>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
        }

        override fun executeAction(action: Action, getState: () -> State) {
            super.executeAction(action, getState)
            when (action) {
                is Action.Load -> {
                    scope.launch {
                        val users = withContext(dispatcherProvider.io) {
                            userRepository.getUsers()
                        }
                        dispatch(Message.UserList(users))
                    }
                }
            }
        }
    }
}
