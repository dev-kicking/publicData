package dev.kick.domain.useCase

import androidx.paging.PagingData
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.repo.OpenDataRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindCaloriesForAgeListUseCase @Inject constructor(
    private val repo: OpenDataRepo
) {
    operator fun invoke(age: Int): Flow<PagingData<CalorieForAge>> = repo.findCalorieForAge(age)
}