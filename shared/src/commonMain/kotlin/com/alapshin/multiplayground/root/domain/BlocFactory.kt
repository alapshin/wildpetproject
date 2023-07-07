package com.alapshin.multiplayground.root.domain

import com.alapshin.multiplayground.base.Bloc
import com.arkivanov.decompose.ComponentContext

typealias BlocFactory = (ComponentContext) -> Bloc<*, *>
