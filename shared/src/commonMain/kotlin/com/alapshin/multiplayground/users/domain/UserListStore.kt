package com.alapshin.multiplayground.users.domain

import com.alapshin.multiplayground.users.domain.UserListMvi.Intent
import com.alapshin.multiplayground.users.domain.UserListMvi.State
import com.arkivanov.mvikotlin.core.store.Store

interface UserListStore : Store<Intent, State, Nothing>
