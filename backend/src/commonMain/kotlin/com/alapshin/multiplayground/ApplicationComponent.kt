package com.alapshin.multiplayground

import com.alapshin.multiplayground.db.DatabaseComponent
import com.alapshin.multiplayground.di.ApplicationScope
import me.tatarka.inject.annotations.Component

@Component
abstract class ApplicationComponent(
    @Component val databaseComponent: DatabaseComponent
)