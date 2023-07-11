package com.alapshin.multiplayground.root.di

import com.alapshin.multiplayground.di.ApplicationComponent
import com.alapshin.multiplayground.landing.di.LandingComponent
import com.alapshin.multiplayground.login.di.LoginComponent
import com.alapshin.multiplayground.registration.di.RegistrationComponent
import com.alapshin.multiplayground.root.domain.Config
import com.alapshin.multiplayground.root.domain.RootBloc
import com.alapshin.multiplayground.root.domain.RootBlocImpl
import com.alapshin.multiplayground.users.di.UserComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Component
@RootScope
abstract class RootComponent(
    private val componentContext: ComponentContext,
    @Component val applicationComponent: ApplicationComponent,
) : LandingComponent, LoginComponent, RegistrationComponent, UserComponent {
    abstract val rootBloc: RootBloc

    @Provides
    @RootScope
    fun navigation(): StackNavigation<Config> {
        return StackNavigation()
    }

    @Provides
    fun rootBloc(bloc: RootBlocImpl): RootBloc = bloc

    @Provides
    fun componentContext(): ComponentContext = componentContext
}

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
private annotation class RootScope
