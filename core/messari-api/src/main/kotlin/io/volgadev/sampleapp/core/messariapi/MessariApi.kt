package io.volgadev.sampleapp.core.messariapi

import PricesResponse
import io.volgadev.sampleapp.core.std.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.volgadev.sampleapp.core.messariapi.models.CryptoProfileResponse
import io.volgadev.sampleapp.core.network.ApiResponseFailure
import io.volgadev.sampleapp.core.network.safeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MessariApi(private val client: HttpClient) {

    suspend fun getPrices(): Result<PricesResponse, ApiResponseFailure> {
        return withContext(Dispatchers.IO) {
            safeRequest {
                client.get {
                    url(ASSETS_URL)
                    parameter("fields", "id,slug,symbol,metrics/market_data/price_usd")
                }
            }
        }
    }

    suspend fun getCryptoProfile(id: String): Result<CryptoProfileResponse, ApiResponseFailure> {
        return withContext(Dispatchers.IO) {
            safeRequest {
                client.get {
                    url("$ASSETS_URL/$id/profile")
                    parameter("fields", "profile/general/overview,profile/general/background")
                }
            }
        }
    }

    private companion object {
        const val BASE_URL = "https://data.messari.io/api/v2"
        const val ASSETS_URL = "$BASE_URL/assets"
    }
}
