package io.volgadev.sampleapp.core.messariapi.data

import AssetsResponse
import io.volgadev.core.result.Result
import io.volgadev.sampleapp.core.network.ApiResponseFailure

interface MessariApi {
    suspend fun loadAssets(): Result<AssetsResponse, ApiResponseFailure>
}