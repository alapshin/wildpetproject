package com.alapshin.multiplayground.login.domain

import androidx.compose.runtime.Composable
import com.alapshin.multiplayground.compound.Bloc
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class LoginBloc(
    private val presenter: LoginPresenter,
    private val navigation: StackNavigation<Config>,
    private val componentContext: ComponentContext,
) : Bloc<LoginMvi.Event, LoginMvi.State>(componentContext) {

    fun navigateToMain() {
        navigation.push(Config.UserList)
    }

    @Composable
    override fun state(events: Flow<LoginMvi.Event>): LoginMvi.State {
        return presenter.state(events)
    }
}
