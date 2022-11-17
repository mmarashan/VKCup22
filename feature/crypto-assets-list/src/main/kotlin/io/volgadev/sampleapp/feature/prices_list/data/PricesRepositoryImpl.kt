package io.volgadev.sampleapp.feature.prices_list.data

import PriceDto
import io.volgadev.sampleapp.core.messariapi.MessariApi
import io.volgadev.sampleapp.core.std.Result
import io.volgadev.sampleapp.feature.prices_list.domain.PricesRepository
import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset

internal class PricesRepositoryImpl(
    private val api: MessariApi
) : PricesRepository {

    override suspend fun getPrices(): Result<List<CryptoAsset>, Throwable> {
        return when (val pricesResult = api.getPrices()) {
            is Result.Error -> {
                Result.Error(pricesResult.exception)
            }
            is Result.Success -> {
                Result.Success(pricesResult.value.prices.mapToEntity())
            }
        }
    }

    private fun List<PriceDto>.mapToEntity(): List<CryptoAsset> {
        return map { priceDto ->
            CryptoAsset(
                id = priceDto.id, name = priceDto.symbol, price = priceDto.metrics.marketData.price
            )
        }
    }
}
