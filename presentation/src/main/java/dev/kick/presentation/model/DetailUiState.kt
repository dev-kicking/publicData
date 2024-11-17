package dev.kick.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import dev.kick.domain.model.CalorieForAge

@Stable
sealed class DetailUiState {
    @Immutable
    data object Loading: DetailUiState()

    @Immutable
    data object Error: DetailUiState()

    @Immutable
    data class Success(
        val data: CalorieForAge
    ): DetailUiState()
}