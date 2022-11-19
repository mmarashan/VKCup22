package io.volgadev.sampleapp.core.messariapi.data

import AssetsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.volgadev.core.result.Result
import io.volgadev.sampleapp.core.network.ApiResponseFailure
import io.volgadev.sampleapp.core.network.safeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal class MessariApiImpl(private val client: HttpClient) : MessariApi {

    override suspend fun loadAssets(): Result<AssetsResponse, ApiResponseFailure> {
        return withContext(Dispatchers.IO) {
            safeRequest {
                client.get {
                    url(ASSETS_URL)
                    parameter("fields", "id,slug,symbol,metrics/market_data/price_usd")
                }
            }
        }
    }

    private companion object {
        const val BASE_URL = "https://data.messari.io/api/v2"
        const val ASSETS_URL = "$BASE_URL/assets"
    }
}