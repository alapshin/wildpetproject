package com.alapshin.multiplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.alapshin.multiplayground.di.ApplicationComponent
import com.alapshin.multiplayground.di.PlatformComponent
import com.alapshin.multiplayground.di.create
import com.alapshin.multiplayground.root.di.RootComponent
import com.alapshin.multiplayground.root.di.create
import com.alapshin.multiplayground.root.view.RootScreen
import com.alapshin.multiplayground.theme.AppTheme
import com.arkivanov.decompose.defaultComponentContext
import io.kamel.image.config.LocalKamelConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val platformComponent = PlatformComponent::class.create(this)
        val applicationComponent = ApplicationComponent::class.create(
            platformComponent = platformComponent
        )
        val decomposeComponent = RootComponent::class.create(
            componentContext = defaultComponentContext(),
            applicationComponent = applicationComponent,
        )
        val kamelConfig = platformComponent.kamelConfig

        setContent {
            AppTheme {
                CompositionLocalProvider(LocalKamelConfig provides kamelConfig) {
                    RootScreen(bloc = decomposeComponent.rootBloc, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
