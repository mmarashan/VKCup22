package io.volgadev.sampleapp.feature.prices_list.presentation.model

import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset

internal sealed class AssetsListScreenState {

    data class Content(
        val items: List<CryptoAsset>
    ) : AssetsListScreenState()

    object Error : AssetsListScreenState()

    object Loading : AssetsListScreenState()
}