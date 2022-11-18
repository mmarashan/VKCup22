package io.volgadev.sampleapp.core.network

import io.volgadev.core.result.Result
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.statement.readText
import java.io.IOException
import kotlinx.serialization.SerializationException


sealed class ApiResponseFailure(e: Exception? = null) : Throwable(e) {
    data class HttpFailure(val code: Int, val errorBody: String) : ApiResponseFailure()
    class NetworkFailure(e: Exception? = null) : ApiResponseFailure(e)
    class SerializationFailure(e: Exception? = null) : ApiResponseFailure(e)
}

suspend inline fun <reified T> safeRequest(block: () -> T): Result<T, ApiResponseFailure> {

    return try {
        Result.Success(block())
    } catch (e: ClientRequestException) {
        Result.Error(
            ApiResponseFailure.HttpFailure(
                e.response.status.value,
                e.response.readText(),
            )
        )
    } catch (e: ServerResponseException) {
        Result.Error(
            ApiResponseFailure.HttpFailure(
                e.response.status.value,
                e.response.content.toString(),
            )
        )
    } catch (e: IOException) {
        Result.Error(ApiResponseFailure.NetworkFailure(e))
    } catch (e: SerializationException) {
        Result.Error(ApiResponseFailure.SerializationFailure(e))
    }
}