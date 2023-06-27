package com.alapshin.multiplayground.db

interface DatabaseFactory {
    fun createDatabase(filename: String): Database
}
