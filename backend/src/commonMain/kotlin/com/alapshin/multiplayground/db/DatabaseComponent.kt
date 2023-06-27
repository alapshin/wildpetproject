package com.alapshin.multiplayground.db

import com.alapshin.multiplayground.di.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class DatabaseComponent(private val filename: String) {
    @Provides
    fun database(databaseFactory: DatabaseFactory): Database {
        return databaseFactory.createDatabase(filename)
    }

    @Provides
    fun sqlDriverFactory(): SqlDriverFactory = SqlDriverFactory()

    @Provides
    fun provideDatabaseFactory(driverFactory: SqlDriverFactory): DatabaseFactory {
        return DefaultDbFactory(driverFactory)
    }
}
