package com.alapshin.multiplayground

import com.alapshin.multiplayground.auth.data.AuthRepository
import com.alapshin.multiplayground.auth.data.AuthRepositoryImpl
import com.alapshin.multiplayground.auth.presentation.AuthController
import com.alapshin.multiplayground.auth.service.AuthService
import com.alapshin.multiplayground.auth.service.AuthServiceImpl
import com.alapshin.multiplayground.config.ConfigComponent
import com.alapshin.multiplayground.core.Controller
import com.alapshin.multiplayground.core.ControllerManager
import com.alapshin.multiplayground.db.DatabaseComponent
import com.alapshin.multiplayground.jwt.JwtComponent
import com.alapshin.multiplayground.user.data.UserRepository
import com.alapshin.multiplayground.user.data.UserRepositoryImpl
import com.alapshin.multiplayground.user.service.UserService
import com.alapshin.multiplayground.user.service.UserServiceImpl
import com.alapshin.multiplayground.user.presentation.UserControler
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Component
@ApplicationScope
abstract class ApplicationComponent(
    @Component val jwtComponent: JwtComponent,
    @Component val configComponent: ConfigComponent,
    @Component val databaseComponent: DatabaseComponent,
) {
    abstract val routerManager: ControllerManager

    @Provides
    @ApplicationScope
    fun controllerManager(controllers: Set<Controller>) = ControllerManager(controllers)

    @Provides
    @ApplicationScope
    fun authService(service: AuthServiceImpl): AuthService = service

    @IntoSet
    @Provides
    @ApplicationScope
    fun authController(controller: AuthController): Controller = controller

    @Provides
    @ApplicationScope
    fun authRepository(repository: AuthRepositoryImpl): AuthRepository = repository


    @Provides
    @ApplicationScope
    fun userService(service: UserServiceImpl): UserService = service

    @IntoSet
    @Provides
    @ApplicationScope
    fun userController(controller: UserControler): Controller = controller

    @Provides
    @ApplicationScope
    fun userRepository(repository: UserRepositoryImpl): UserRepository = repository
}

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class ApplicationScope
