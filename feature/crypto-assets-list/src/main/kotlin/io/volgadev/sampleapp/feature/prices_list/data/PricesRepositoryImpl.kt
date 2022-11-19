package io.volgadev.sampleapp.feature.prices_list.data

import io.volgadev.core.result.Result
import io.volgadev.sampleapp.core.messariapi.MessariApi
import io.volgadev.sampleapp.feature.prices_list.data.mapper.AssetsMapper
import io.volgadev.sampleapp.feature.prices_list.domain.PricesRepository
import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset

internal class PricesRepositoryImpl(
    private val api: MessariApi,
    private val assetsMapper: AssetsMapper
) : PricesRepository {

    override suspend fun loadAssets(): Result<List<CryptoAsset>, Throwable> {
        return when (val pricesResult = api.loadAssets()) {
            is Result.Error -> {
                Result.Error(pricesResult.exception)
            }
            is Result.Success -> {
                Result.Success(assetsMapper.mapToEntity(pricesResult.value.prices))
            }
        }
    }
}
