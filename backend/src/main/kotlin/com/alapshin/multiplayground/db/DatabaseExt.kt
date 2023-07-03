package com.alapshin.multiplayground.db

import app.cash.sqldelight.TransactionWithReturn

fun Database.runGettingLastId(
    body: TransactionWithReturn<Long>.() -> Unit,
): Long {
    return transactionWithResult {
        body(this)
        utilsQueries.lastInsertedRowId().executeAsOne()
    }
}
