package com.alapshin.multiplayground.root.domain

import com.alapshin.multiplayground.list.domain.ListBloc

sealed interface Child {
    class Main(val bloc: ListBloc): Child
}