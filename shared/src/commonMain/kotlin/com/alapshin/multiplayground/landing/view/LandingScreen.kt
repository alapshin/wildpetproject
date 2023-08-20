package com.alapshin.multiplayground.landing.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.LocalStrings
import com.alapshin.multiplayground.theme.Defaults
import com.alapshin.multiplayground.landing.domain.LandingBloc

@Composable
fun LandingScreen(bloc: LandingBloc) {
    val state by bloc.state.collectAsState()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(Defaults.ContentPadding),
    ) {
        Button(
            onClick = {
                bloc.navigateToLogin()
            },
            contentPadding = ButtonDefaults.ContentPadding,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(LocalStrings.current.login)
        }
        Spacer(modifier = Modifier.size(Defaults.ContentVerticalSpacing))
        FilledTonalButton(
            onClick = {
                bloc.navigateToRegistration()
            },
            contentPadding = ButtonDefaults.ContentPadding,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(LocalStrings.current.register)
        }
    }
}
