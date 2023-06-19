package com.alapshin.multiplayground.users.domain

import com.alapshin.multiplayground.compound.Presenter

interface UserListPresenter : Presenter<UserListMvi.Event, UserListMvi.State>
