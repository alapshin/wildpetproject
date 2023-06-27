package com.alapshin.multiplayground.db

import app.cash.sqldelight.db.SqlDriver
import me.tatarka.inject.annotations.Inject

expect class SqlDriverFactory constructor() {
    fun createDriver(filename: String): SqlDriver
}