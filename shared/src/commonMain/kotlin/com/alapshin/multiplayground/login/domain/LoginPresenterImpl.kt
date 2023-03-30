package com.alapshin.multiplayground.login.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alapshin.multiplayground.coroutines.DispatcherProvider
import com.alapshin.multiplayground.login.data.LoginManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
class LoginPresenterImpl(
    private val loginManager: LoginManager,
    private val dispatcherProvider: DispatcherProvider
) : LoginPresenter {
    @Composable
    override fun state(events: Flow<LoginMvi.Event>): LoginMvi.State {
        var email: String? by remember { mutableStateOf(null) }
        var password: String? by remember { mutableStateOf(null) }
        var success: Boolean by remember { mutableStateOf(false) }

        LaunchedEffect(email, password) {
            val e = email
            val p = password
            if (e != null && p != null) {
                success = withContext(dispatcherProvider.io) {
                    loginManager.login(e, p)
                }.let { it.token != null }
            }
        }

        LaunchedEffect(Unit) {
            events.collect { event ->
                when (event) {
                    is LoginMvi.Event.Login -> {
                        email = event.email
                        password = event.password
                    }
                }
            }
        }

        return LoginMvi.State(success = success)
    }
}
