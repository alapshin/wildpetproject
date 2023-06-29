package com.alapshin.multiplayground

import com.alapshin.multiplayground.auth.model.AuthRepository
import com.alapshin.multiplayground.auth.model.AuthRepositoryImpl
import com.alapshin.multiplayground.auth.route.AuthController
import com.alapshin.multiplayground.auth.route.AuthControllerImpl
import com.alapshin.multiplayground.auth.route.AuthRouter
import com.alapshin.multiplayground.core.Router
import com.alapshin.multiplayground.core.RouterManager
import com.alapshin.multiplayground.db.DatabaseComponent
import com.alapshin.multiplayground.di.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class ApplicationComponent(
    @Component val databaseComponent: DatabaseComponent,
) {
    abstract val routerManager: RouterManager

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
}
