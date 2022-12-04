package io.volgadev.sampleapp.project

object Modules {

    object Core {
        const val common = ":core:common"
        const val network = ":core:network"
        const val featureApi = ":core:feature-api"
        const val uiKit = ":core:ui-kit"
    }

    object Feature {
        const val dzenTopics = ":feature:dzen-topics"
    }
}