package com.alapshin.multiplayground.login.domain

import com.arkivanov.mvikotlin.core.store.Store

interface LoginStore : Store<LoginMvi.Intent, LoginMvi.State, Nothing>
