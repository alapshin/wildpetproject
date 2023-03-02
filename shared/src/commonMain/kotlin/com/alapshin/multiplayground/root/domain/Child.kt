package com.alapshin.multiplayground.root.domain

import com.alapshin.multiplayground.users.domain.UserListBloc

sealed interface Child {
    class UserList(val bloc: UserListBloc) : Child
}
