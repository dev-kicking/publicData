package dev.kick.presentation.model

sealed interface CaloriesForAgeDetailUiEffect {
    data class NotFoundDetail(
        val message: String,
    ): CaloriesForAgeDetailUiEffect
}