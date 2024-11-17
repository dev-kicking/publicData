package dev.kick.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.kick.data.datasource.AppDatabase
import dev.kick.data.datasource.CaloriesForAgeRemoteMediator
import dev.kick.data.service.OpenDataService
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.repo.AppApplication
import dev.kick.domain.repo.OpenDataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class OpenDataRepoImpl @Inject constructor(
    private val service: OpenDataService,
    private val appApplication: AppApplication,
    private val appDatabase: AppDatabase,
) : OpenDataRepo {
    override fun getCalorieForAge(): Flow<PagingData<CalorieForAge>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = CaloriesForAgeRemoteMediator(service, appApplication, appDatabase),
            pagingSourceFactory = { appDatabase.calorieForAgeDao().getCalorieForAgePagingSource() }
        ).flow
    }

    override fun getLocalCalorieForAge(): Flow<PagingData<CalorieForAge>> {
        return Pager(
            config = PagingConfig(pageSize = 100),
            pagingSourceFactory = { appDatabase.calorieForAgeDao().getCalorieForAgePagingSource() }
        ).flow
    }

    override fun findCalorieForAge(age: Int): Flow<PagingData<CalorieForAge>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { appDatabase.calorieForAgeDao().findCalorieForAgePagingSource(age) }
        ).flow
    }
}