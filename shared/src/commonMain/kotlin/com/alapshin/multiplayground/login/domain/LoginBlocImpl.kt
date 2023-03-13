package com.alapshin.multiplayground.login.domain

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.flow.StateFlow
import me.tatarka.inject.annotations.Inject

@Inject
internal class LoginBlocImpl(
    private val storeFactory: LoginStoreFactory,
    private val componentContext: ComponentContext,
) : LoginBloc, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore {
        storeFactory.create()
    }

    override val state: StateFlow<LoginMvi.State> = store.stateFlow

    override fun onLoginClicked(email: String, password: String) {
        store.accept(LoginMvi.Intent.Login(email, password))
    }
}
