package dev.kick.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface CaloriesForAgeNavigation {
    @Serializable
    data object ListScreen : CaloriesForAgeNavigation

    @Serializable
    data class DetailScreen(
        val id: String
    ) : CaloriesForAgeNavigation
}