rootProject.name = "Ktor example"

/* App */
include(
    ":app"
)

/* Core */
include(
    ":core:network",
    ":core:std",
    ":core:feature-api",
    ":core:messari-api",
)

/* Feature */
include(
    ":feature:crypto-assets-list"
)
