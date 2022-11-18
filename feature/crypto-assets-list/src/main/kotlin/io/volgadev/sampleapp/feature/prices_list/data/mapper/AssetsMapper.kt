package io.volgadev.sampleapp.feature.prices_list.data.mapper

import AssetDto
import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset

internal interface AssetsMapper {
    fun mapToEntity(dto: List<AssetDto>): List<CryptoAsset>
}

internal class AssetsMapperImpl : AssetsMapper {

    override fun mapToEntity(dto: List<AssetDto>): List<CryptoAsset> {
        return dto.map { priceDto ->
            CryptoAsset(
                id = priceDto.id, name = priceDto.symbol, price = priceDto.metrics.marketData.price
            )
        }
    }
}