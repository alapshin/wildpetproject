package com.alapshin.multiplayground.root.domain

import com.alapshin.multiplayground.login.domain.LoginBloc
import com.alapshin.multiplayground.registration.domain.RegistrationBloc
import com.alapshin.multiplayground.users.domain.UserListBloc

sealed interface Child {
    class Login(val bloc: LoginBloc) : Child
    class Registration(val bloc: RegistrationBloc) : Child
    class UserList(val bloc: UserListBloc) : Child
}
