package io.volgadev.sampleapp.feature.prices_list.domain

import io.volgadev.core.result.Result
import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset

internal interface PricesRepository {
    suspend fun loadAssets(): Result<List<CryptoAsset>, Throwable>
}
