package com.alapshin.multiplayground.root.domain

import com.alapshin.multiplayground.landing.domain.LandingBloc
import com.alapshin.multiplayground.login.domain.LoginBloc
import com.alapshin.multiplayground.registration.domain.RegistrationBloc
import com.alapshin.multiplayground.users.domain.UserListBloc
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import me.tatarka.inject.annotations.Inject

@Inject
class RootBlocImpl(
    private val navigation: StackNavigation<Config>,
    private val componentContext: ComponentContext,
    private val blocFactoryMap: Map<Config, BlocFactory>,
) : RootBloc, ComponentContext by componentContext {

    private val _stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::child,
        initialConfiguration = Config.Landing,
    )
    override val stack: Value<ChildStack<*, Child>> = _stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        val blocFactory = checkNotNull(blocFactoryMap[config])
        return when (config) {
            Config.Landing -> Child.Landing(blocFactory(componentContext) as LandingBloc)
            Config.Login -> Child.Login(blocFactory(componentContext) as LoginBloc)
            Config.Registration -> Child.Registration(blocFactory(componentContext) as RegistrationBloc)
            Config.UserList -> Child.UserList(blocFactory(componentContext) as UserListBloc)
        }
    }
}
