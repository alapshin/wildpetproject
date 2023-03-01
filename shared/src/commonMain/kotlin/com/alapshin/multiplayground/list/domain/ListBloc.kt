package com.alapshin.multiplayground.list.domain

import com.arkivanov.decompose.ComponentContext

interface ListBloc

typealias MainBlocFactory = (ComponentContext) -> ListBloc