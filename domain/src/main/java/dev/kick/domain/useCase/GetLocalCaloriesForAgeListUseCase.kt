package dev.kick.domain.useCase

import androidx.paging.PagingData
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.repo.LocalCaloriesForAgeRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCaloriesForAgeListUseCase @Inject constructor(
    private val repo: LocalCaloriesForAgeRepo
) {
    operator fun invoke(): Flow<PagingData<CalorieForAge>> = repo.getLocalCalorieForAgeList()
}