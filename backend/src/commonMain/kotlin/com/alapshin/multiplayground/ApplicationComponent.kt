package com.alapshin.multiplayground

import com.alapshin.multiplayground.db.DatabaseComponent
import me.tatarka.inject.annotations.Component

@Component
abstract class ApplicationComponent(
    @Component val databaseComponent: DatabaseComponent
)
