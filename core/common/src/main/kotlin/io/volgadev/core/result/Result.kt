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
     * Возвщащенная ошибка, опционально скрывающая причину в [exception]
     */
    data class Error<out E : Throwable>(val exception: E) : Result<Nothing, E>()

    /**
     * Возвращенный успешный ответ типа [T]
     */
    data class Success<T>(val value: T) : Result<T, Nothing>()
}
