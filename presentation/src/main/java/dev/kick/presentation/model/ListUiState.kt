package dev.kick.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import dev.kick.domain.model.CalorieForAge
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
sealed class ListUiState {
    @Immutable
    data object Loading: ListUiState()

    @Immutable
    data object Error: ListUiState()

    @Immutable
    data class Success(
        val caloriesForAgeList: Flow<PagingData<CalorieForAge>> = emptyFlow(),
        val text: String = ""
    ): ListUiState()
}