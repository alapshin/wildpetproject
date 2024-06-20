package com.alapshin.multiplayground.root.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alapshin.multiplayground.landing.view.LandingScreen
import com.alapshin.multiplayground.login.view.LoginScreen
import com.alapshin.multiplayground.registration.view.RegistrationScreen
import com.alapshin.multiplayground.root.domain.Child
import com.alapshin.multiplayground.root.domain.RootBloc
import com.alapshin.multiplayground.users.view.UserListScreen
import com.arkivanov.decompose.extensions.compose.stack.Children

@Composable
fun RootScreen(bloc: RootBloc, modifier: Modifier) {
    Scaffold {
        Children(
            stack = bloc.stack,
            modifier = modifier,
        ) {
            when (val child = it.instance) {
                is Child.Landing -> LandingScreen(bloc = child.bloc)
                is Child.Login -> LoginScreen(bloc = child.bloc)
                is Child.Registration -> RegistrationScreen(bloc = child.bloc)
                is Child.UserList -> UserListScreen(bloc = child.bloc)
            }
        }
    }
}
