package com.alapshin.multiplayground

import com.alapshin.multiplayground.auth.model.AuthRepository
import com.alapshin.multiplayground.auth.model.AuthRepositoryImpl
import com.alapshin.multiplayground.auth.route.AuthController
import com.alapshin.multiplayground.auth.route.AuthControllerImpl
import com.alapshin.multiplayground.auth.route.AuthRouter
import com.alapshin.multiplayground.config.ConfigComponent
import com.alapshin.multiplayground.core.Router
import com.alapshin.multiplayground.core.RouterManager
import com.alapshin.multiplayground.db.DatabaseComponent
import com.alapshin.multiplayground.jwt.TokenCreator
import com.alapshin.multiplayground.user.model.UserRepository
import com.alapshin.multiplayground.user.model.UserRepositoryImpl
import com.alapshin.multiplayground.user.route.UserController
import com.alapshin.multiplayground.user.route.UserControllerImpl
import com.alapshin.multiplayground.user.route.UserRouter
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Component
@ApplicationScope
abstract class ApplicationComponent(
    @Component val configComponent: ConfigComponent,
    @Component val databaseComponent: DatabaseComponent,
) {
    abstract val routerManager: RouterManager

    @Provides
    @ApplicationScope
    fun tokenCreator(): TokenCreator {
        return TokenCreator()
    }

    @Provides
    @ApplicationScope
    fun routerManager(routers: Set<Router>) = RouterManager(routers)

    @IntoSet
    @Provides
    @ApplicationScope
    fun authRouter(controller: AuthController): Router = AuthRouter(controller)

    @Provides
    @ApplicationScope
    fun authController(controller: AuthControllerImpl): AuthController = controller

    @Provides
    @ApplicationScope
    fun authRepository(repository: AuthRepositoryImpl): AuthRepository = repository

    @IntoSet
    @Provides
    @ApplicationScope
    fun userRouter(controller: UserController): Router = UserRouter(controller)

    @Provides
    @ApplicationScope
    fun userController(controller: UserControllerImpl): UserController = controller

    @Provides
    @ApplicationScope
    fun userRepository(repository: UserRepositoryImpl): UserRepository = repository
}

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class ApplicationScope
