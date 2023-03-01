package com.alapshin.multiplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.alapshin.multiplayground.root.di.RootComponent
import com.alapshin.multiplayground.root.di.create
import com.alapshin.multiplayground.root.view.RootContent
import com.alapshin.multiplayground.theme.AppTheme
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootBloc = RootComponent::class.create(defaultComponentContext()).rootBloc

        setContent {
            AppTheme {
               RootContent(bloc = rootBloc, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

