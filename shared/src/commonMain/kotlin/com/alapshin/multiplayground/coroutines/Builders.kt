package com.alapshin.multiplayground.coroutines

import java.util.concurrent.CancellationException

/**
 * Like [runCatching], but with proper coroutines cancellation handling.
 * Cancellation exceptions need to be rethrown.
 * See https://github.com/Kotlin/kotlinx.coroutines/issues/1814.
 */
@Suppress("TooGenericExceptionCaught")
suspend inline fun <R> suspendCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}
