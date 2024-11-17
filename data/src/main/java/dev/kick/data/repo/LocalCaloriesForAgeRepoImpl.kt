package dev.kick.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.kick.data.datasource.AppDatabase
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.repo.LocalCaloriesForAgeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalCaloriesForAgeRepoImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : LocalCaloriesForAgeRepo {
    override fun getLocalCalorieForAgeList(): Flow<PagingData<CalorieForAge>> {
        return Pager(
            config = PagingConfig(pageSize = 100),
            pagingSourceFactory = { appDatabase.calorieForAgeDao().getCalorieForAgePagingSource() }
        ).flow
    }

    override fun findCalorieForAgeList(age: Int): Flow<PagingData<CalorieForAge>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                appDatabase.calorieForAgeDao().findCalorieForAgePagingSource(age)
            }
        ).flow
    }

    override fun getCalorieForAge(id: Int): Flow<CalorieForAge> = flow {
        emit(appDatabase.calorieForAgeDao().getCalorieForAge(id))
    }
}