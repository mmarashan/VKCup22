package io.volgadev.core.result


/**
 * Query result (for data or for calculation) with expected response of type [T] and possible
 * runtime exception [E].
 * Alternative to throwing Exception (developer decides)
 * Although throwing Exception is suggested by Rob Martin in Clean Code most
 * correct behavior, the try-catch construct can bloat the code due to cumbersomeness.
 * The return type must be [Result] and handled at the call site via when(result)
 */
sealed class Result<out T, out E : Throwable> {
    /**
     * The returned error, optionally hiding the cause in [exception]
     */
    data class Error<out E : Throwable>(val exception: E) : Result<Nothing, E>()

    /**
     * Returned successful response of type [T]
     */
    data class Success<T>(val value: T) : Result<T, Nothing>()
}
