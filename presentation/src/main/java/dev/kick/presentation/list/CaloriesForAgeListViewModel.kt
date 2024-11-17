package dev.kick.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kick.domain.useCase.GetCaloriesForAgeListUseCase
import dev.kick.presentation.model.ListUiEffect
import dev.kick.presentation.model.ListUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CaloriesForAgeListViewModel @Inject constructor(
    private val getCaloriesForAgeListUseCase: GetCaloriesForAgeListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)

    val uiState = _uiState.asStateFlow()
    private val _uiEffect = MutableSharedFlow<ListUiEffect>()

    val uiEffect = _uiEffect.asSharedFlow()
    init {
//        viewModelScope.launch {
//            _uiState.value =
//                ListUiState.Success(caloriesForAgeList = getCaloriesForAgeListUseCase().catch {
//                    if (it is UnknownHostException) {
//                        _uiEffect.emit(ListUiEffect.NetworkError("네트워크 연결을 확인해주세요."))
//                    } else {
//                        _uiEffect.emit(
//                            ListUiEffect.NetworkError(it.message ?: "알 수 없는 오류가 발생했습니다.")
//                        )
//                    }
//                }.cachedIn(viewModelScope))
//        }
    }

    fun onClickLike() {

    }
}
