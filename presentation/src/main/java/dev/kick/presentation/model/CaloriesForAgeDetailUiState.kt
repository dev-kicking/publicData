package dev.kick.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import dev.kick.domain.model.CalorieForAge

@Stable
sealed class CaloriesForAgeDetailUiState {
    @Immutable
    data object Loading: CaloriesForAgeDetailUiState()

    @Immutable
    data object Error: CaloriesForAgeDetailUiState()

    @Immutable
    data class Success(
        val data: CalorieForAge
    ): CaloriesForAgeDetailUiState()
}