package com.alapshin.multiplayground.landing.domain

import androidx.compose.runtime.Composable
import com.alapshin.multiplayground.base.Bloc
import com.alapshin.multiplayground.root.domain.Config
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class LandingBloc(
    private val navigation: StackNavigation<Config>,
    private val componentContext: ComponentContext,
) : Bloc<Unit, Unit>(componentContext) {

    fun navigateToLogin() {
        navigation.push(Config.Login)
    }

    fun navigateToRegistration() {
        navigation.push(Config.Registration)
    }

    @Composable
    override fun state(events: Flow<Unit>) {
        return
    }
}
