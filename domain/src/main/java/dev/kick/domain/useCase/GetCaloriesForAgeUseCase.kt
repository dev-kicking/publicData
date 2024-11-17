package dev.kick.domain.useCase

import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.repo.LocalCaloriesForAgeRepo
import dev.kick.domain.repo.OpenDataRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCaloriesForAgeUseCase @Inject constructor(
    private val repo: LocalCaloriesForAgeRepo
) {
    operator fun invoke(id: Int): Flow<CalorieForAge> = repo.getCalorieForAge(id)
}