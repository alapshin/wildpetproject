package com.alapshin.multiplayground.root.domain

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootBloc {
    val stack: Value<ChildStack<*, Child>>
}
