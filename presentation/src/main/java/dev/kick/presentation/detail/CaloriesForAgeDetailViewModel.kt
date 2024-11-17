package dev.kick.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kick.domain.useCase.GetCaloriesForAgeUseCase
import dev.kick.presentation.model.CaloriesForAgeDetailUiEffect
import dev.kick.presentation.model.CaloriesForAgeDetailUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaloriesForAgeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCaloriesForAgeUseCase: GetCaloriesForAgeUseCase,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<CaloriesForAgeDetailUiState>(CaloriesForAgeDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CaloriesForAgeDetailUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        val id: Int = checkNotNull(savedStateHandle["id"])

        viewModelScope.launch {
            getCaloriesForAgeUseCase(
                id = id
            ).catch {
                _uiState.value = CaloriesForAgeDetailUiState.Error
            }.collectLatest { calorieForAge ->
                _uiState.value = CaloriesForAgeDetailUiState.Success(calorieForAge)
            }
        }
    }
}
