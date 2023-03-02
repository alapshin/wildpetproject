package com.alapshin.multiplayground.root.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alapshin.multiplayground.root.domain.Child
import com.alapshin.multiplayground.root.domain.RootBloc
import com.alapshin.multiplayground.users.view.UserListScreen
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation

@Composable
fun RootContent(bloc: RootBloc, modifier: Modifier) {
    Scaffold {
        Children(
            stack = bloc.stack,
            modifier = modifier,
            animation = stackAnimation(fade() + scale()),
        ) {
            when (val child = it.instance) {
                is Child.UserList -> UserListScreen(bloc = child.bloc)
            }
        }
    }
}
