package com.alapshin.multiplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.alapshin.multiplayground.di.ApplicationComponent
import com.alapshin.multiplayground.di.PlatformComponent
import com.alapshin.multiplayground.di.create
import com.alapshin.multiplayground.root.di.DescomposeComponent
import com.alapshin.multiplayground.root.di.create
import com.alapshin.multiplayground.root.view.RootContent
import com.alapshin.multiplayground.theme.AppTheme
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val platformComponent = PlatformComponent::class.create(this)
        val applicationComponent = ApplicationComponent::class.create(
            platformComponent = platformComponent
        )
        val decomposeComponent = DescomposeComponent::class.create(
            componentContext = defaultComponentContext(),
            applicationComponent = applicationComponent,
        )

        setContent {
            AppTheme {
                RootContent(bloc = decomposeComponent.rootBloc, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
