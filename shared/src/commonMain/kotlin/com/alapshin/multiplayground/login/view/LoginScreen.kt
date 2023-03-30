package com.alapshin.multiplayground.login.view

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.alapshin.multiplayground.login.domain.LoginBloc
import com.alapshin.multiplayground.login.domain.LoginMvi
import com.alapshin.multiplayground.theme.Defaults

@Composable
fun LoginScreen(bloc: LoginBloc) {
    val state by bloc.state.collectAsState()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(Defaults.ContentPadding)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Defaults.ContentVerticalSpacing))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Defaults.ContentVerticalSpacing))
        Button(
            onClick = {
                bloc.accept(LoginMvi.Event.Login(email, password))
            },
            contentPadding = ButtonDefaults.ContentPadding,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.size(Defaults.ContentVerticalSpacing))
        FilledTonalButton(
            onClick = {
                bloc.navigateToMain()
            },
            contentPadding = ButtonDefaults.ContentPadding,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register ${state.value}")
        }
    }
}
