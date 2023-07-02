package com.alapshin.multiplayground.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

class SqlDriverFactory {
    fun createDriver(filename: String): SqlDriver {
        val url = "jdbc:sqlite:$filename"
        return JdbcSqliteDriver(url).also {
            Database.Schema.create(it)
        }
    }
}
