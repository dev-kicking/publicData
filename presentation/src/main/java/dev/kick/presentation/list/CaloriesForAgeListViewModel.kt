package dev.kick.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kick.domain.useCase.FindCaloriesForAgeListUseCase
import dev.kick.domain.useCase.GetCaloriesForAgeListUseCase
import dev.kick.domain.useCase.GetLocalCaloriesForAgeListUseCase
import dev.kick.presentation.model.ListUiEffect
import dev.kick.presentation.model.ListUiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CaloriesForAgeListViewModel @Inject constructor(
    private val getCaloriesForAgeListUseCase: GetCaloriesForAgeListUseCase,
    private val getLocalCaloriesForAgeListUseCase: GetLocalCaloriesForAgeListUseCase,
    private val findCaloriesForAgeListUseCase: FindCaloriesForAgeListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<ListUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            _uiState.value =
                ListUiState.Success(caloriesForAgeList = getCaloriesForAgeListUseCase().catch {
                    if (it is UnknownHostException) {
                        getList()
                    } else {
                        _uiEffect.emit(
                            ListUiEffect.NetworkError(it.message ?: "알 수 없는 오류가 발생했습니다.")
                        )
                    }
                }.cachedIn(viewModelScope))
        }
    }

    private fun getList() {
        viewModelScope.launch {
            _uiState.value = ListUiState.Success(
                caloriesForAgeList = getLocalCaloriesForAgeListUseCase().catch {
                    _uiEffect.emit(
                        ListUiEffect.NetworkError(it.message ?: "알 수 없는 오류가 발생했습니다.")
                    )
                }.cachedIn(viewModelScope)
            )
        }
    }

    @OptIn(FlowPreview::class)
    fun onTextChange(text: String) {
        val state = _uiState.value
        if (state !is ListUiState.Success) return

        val age = text.toIntOrNull()

        if (text.isEmpty()) {
            _uiState.value = state.copy(text = text)
            getList()
            return
        }

        viewModelScope.launch {
            age?.let {
                _uiState.value = ListUiState.Success(
                    caloriesForAgeList = findCaloriesForAgeListUseCase(
                        age = age
                    ).catch {
                        _uiEffect.emit(
                            ListUiEffect.NetworkError(it.message ?: "알 수 없는 오류가 발생했습니다.")
                        )
                    }.debounce(300).cachedIn(viewModelScope),
                    text = text
                )
            }
        }
    }
}
