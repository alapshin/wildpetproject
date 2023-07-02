package com.alapshin.multiplayground

import com.alapshin.multiplayground.auth.model.AuthRepository
import com.alapshin.multiplayground.auth.model.AuthRepositoryImpl
import com.alapshin.multiplayground.auth.route.AuthController
import com.alapshin.multiplayground.auth.route.AuthControllerImpl
import com.alapshin.multiplayground.auth.route.AuthRouter
import com.alapshin.multiplayground.config.Config
import com.alapshin.multiplayground.config.ConfigComponent
import com.alapshin.multiplayground.core.Router
import com.alapshin.multiplayground.core.RouterManager
import com.alapshin.multiplayground.db.DatabaseComponent
import com.alapshin.multiplayground.di.ApplicationScope
import com.alapshin.multiplayground.jwt.TokenCreator
import com.auth0.jwk.JwkProvider
import com.auth0.jwk.JwkProviderBuilder
import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

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
}
