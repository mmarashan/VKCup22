package io.volgadev.sampleapp.feature.prices_list.presentation

import androidx.lifecycle.viewModelScope
import io.volgadev.core.architecture.SEAViewModel
import io.volgadev.sampleapp.feature.prices_list.domain.PricesRepository
import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset
import io.volgadev.sampleapp.feature.prices_list.presentation.model.AssetsListScreenAction
import io.volgadev.sampleapp.feature.prices_list.presentation.model.AssetsListScreenEvent
import io.volgadev.sampleapp.feature.prices_list.presentation.model.AssetsListScreenState
import kotlinx.coroutines.launch

internal class AssetsListViewModel(
    private val pricesRepository: PricesRepository
) : SEAViewModel<AssetsListScreenState, AssetsListScreenEvent, AssetsListScreenAction>() {

    init {
        onStart()
    }

    override fun onEvent(event: AssetsListScreenEvent) {
        when (event) {
            is AssetsListScreenEvent.ItemClicked -> onItemClicked(event.item)
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            pricesRepository.loadAssets()
        }
    }

    private fun onItemClicked(item: CryptoAsset) {

    }
}