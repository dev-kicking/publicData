package dev.kick.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kick.domain.useCase.GetCaloriesForAgeListUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCaloriesForAgeListUseCase: GetCaloriesForAgeListUseCase,
): ViewModel() {
    
}