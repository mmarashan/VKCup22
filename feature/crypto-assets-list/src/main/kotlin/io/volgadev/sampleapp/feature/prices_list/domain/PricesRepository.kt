package io.volgadev.sampleapp.feature.prices_list.domain

import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset
import io.volgadev.sampleapp.core.std.Result

internal interface PricesRepository {
    suspend fun getPrices(): Result<List<CryptoAsset>, Throwable>
}
