package com.alapshin.multiplayground.db

import me.tatarka.inject.annotations.Inject
@Inject
class DefaultDbFactory constructor(private val driverFactory: SqlDriverFactory) : DatabaseFactory {
    override fun createDatabase(filename: String): Database {
        return Database(driverFactory.createDriver(filename))
    }
}
