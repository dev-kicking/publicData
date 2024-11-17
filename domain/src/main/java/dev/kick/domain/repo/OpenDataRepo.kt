package dev.kick.domain.repo

import androidx.paging.PagingData
import dev.kick.domain.model.CalorieForAge
import kotlinx.coroutines.flow.Flow

interface OpenDataRepo {
    fun getCalorieForAgeList(): Flow<PagingData<CalorieForAge>>
}