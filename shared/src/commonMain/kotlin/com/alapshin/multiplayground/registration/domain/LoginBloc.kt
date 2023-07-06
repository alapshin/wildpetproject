package com.alapshin.multiplayground.registration.domain

import androidx.compose.runtime.Composable
import com.alapshin.multiplayground.base.Bloc
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class RegistrationBloc(
    private val presenter: RegistrationPresenter,
    private val navigation: StackNavigation<Config>,
    private val componentContext: ComponentContext,
) : Bloc<RegistrationMvi.Event, RegistrationMvi.State>(componentContext) {

    fun navigateToMain() {
        navigation.push(Config.UserList)
    }

    @Composable
    override fun state(events: Flow<RegistrationMvi.Event>): RegistrationMvi.State {
        return presenter.state(events)
    }
}
