package com.alapshin.multiplayground.db

import app.cash.sqldelight.db.SqlDriver

expect class SqlDriverFactory constructor() {
    fun createDriver(filename: String): SqlDriver
}
