package com.alapshin.multiplayground.db

import app.cash.sqldelight.db.SqlDriver
import com.alapshin.multiplayground.di.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class DatabaseComponent(private val filename: String) {
    @Provides
    @ApplicationScope
    fun database(databaseFactory: DatabaseFactory): Database {
        return databaseFactory.createDatabase(filename)
    }

    @Provides
    @ApplicationScope
    fun sqlDriverFactory(): SqlDriverFactory = SqlDriverFactory()

    @Provides
    @ApplicationScope
    fun provideDatabaseFactory(driverFactory: SqlDriverFactory): DatabaseFactory {
        return DefaultDbFactory(driverFactory)
    }
}