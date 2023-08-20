package com.alapshin.multiplayground.db

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Component
@DatabaseScope
@Suppress("UnnecessaryAbstractClass")
abstract class DatabaseComponent(private val filename: String) {
    @Provides
    @DatabaseScope
    fun database(databaseFactory: DatabaseFactory): Database {
        return databaseFactory.createDatabase(filename)
    }

    @Provides
    @DatabaseScope
    fun sqlDriverFactory(): SqlDriverFactory = SqlDriverFactory()

    @Provides
    @DatabaseScope
    fun provideDatabaseFactory(driverFactory: SqlDriverFactory): DatabaseFactory {
        return DefaultDbFactory(driverFactory)
    }
}

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class DatabaseScope
