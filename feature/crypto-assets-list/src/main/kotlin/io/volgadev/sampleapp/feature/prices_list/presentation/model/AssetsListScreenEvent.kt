package io.volgadev.sampleapp.feature.prices_list.presentation.model

import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset

internal sealed class AssetsListScreenEvent {

    class ItemClicked(val item: CryptoAsset) : AssetsListScreenEvent()
}