package com.alapshin.multiplayground.login.domain

import com.alapshin.multiplayground.coroutines.DispatcherProvider
import com.alapshin.multiplayground.login.data.LoginManager
import com.alapshin.multiplayground.login.domain.LoginMvi.Intent
import com.alapshin.multiplayground.login.domain.LoginMvi.State
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
class LoginStoreFactory(
    private val storeFactory: StoreFactory,
    private val loginManager: LoginManager,
    private val dispatcherProvider: DispatcherProvider,
) {
    fun create(): LoginStore {
        return object :
            LoginStore,
            Store<Intent, State, Nothing> by storeFactory.create(
                name = LoginStore::class.simpleName,
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
        object LoginSuccess : Message
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                is Message.LoginSuccess -> copy(success = true)
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Nothing>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
            when (intent) {
                is Intent.Login -> {
                    scope.launch {
                        withContext(dispatcherProvider.io) {
                            loginManager.login(intent.email, intent.password).also {
                                dispatch(Message.LoginSuccess)
                            }
                        }
                    }
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            super.executeAction(action, getState)
        }
    }
}
