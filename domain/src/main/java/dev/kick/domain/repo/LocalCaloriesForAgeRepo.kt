package dev.kick.domain.repo

import androidx.paging.PagingData
import dev.kick.domain.model.CalorieForAge
import kotlinx.coroutines.flow.Flow

interface LocalCaloriesForAgeRepo {
    fun getLocalCalorieForAgeList(): Flow<PagingData<CalorieForAge>>
    fun findCalorieForAgeList(age: Int): Flow<PagingData<CalorieForAge>>
    fun getCalorieForAge(id: Int): Flow<CalorieForAge>
}