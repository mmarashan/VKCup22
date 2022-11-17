package io.volgadev.sampleapp.project

object Modules {

    object Core {
        const val std = ":core:std"
        const val network = ":core:network"
        const val messariApi = ":core:messari-api"
        const val featureApi = ":core:feature-api"
    }

    object Feature {
        const val cryptoAssetsList = ":feature:crypto-assets-list"
    }
}