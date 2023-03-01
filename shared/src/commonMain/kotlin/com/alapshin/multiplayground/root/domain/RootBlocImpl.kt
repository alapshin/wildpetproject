package com.alapshin.multiplayground.root.domain

import com.alapshin.multiplayground.list.domain.ListBloc
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import me.tatarka.inject.annotations.Inject

@Inject
class RootBlocImpl(
    private val componentContext: ComponentContext,
    private val blocFactoryMap: Map<Config, BlocFactory>
) : RootBloc, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        initialConfiguration = Config.Main,
        handleBackButton = true,
        childFactory = ::child
    )
    override val stack: Value<ChildStack<*, Child>> = _stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        val blocFactory = checkNotNull(blocFactoryMap[config])
        return when(config) {
            Config.Main -> Child.Main(blocFactory.invoke(componentContext) as ListBloc)
        }
    }
}