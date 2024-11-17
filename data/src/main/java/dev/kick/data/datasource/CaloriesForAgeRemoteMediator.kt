package dev.kick.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.kick.data.mapper.toDomain
import dev.kick.data.service.OpenDataService
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.repo.AppApplication
import java.net.UnknownHostException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CaloriesForAgeRemoteMediator @Inject constructor(
    private val openDataService: OpenDataService,
    private val appApplication: AppApplication,
    private val appDatabase: AppDatabase,
) : RemoteMediator<Int, CalorieForAge>() {

    private val calorieForAgeDao = appDatabase.calorieForAgeDao()
    private val calorieForAgeRemoteKeyDao = appDatabase.caloriesForAgeRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CalorieForAge>,
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    calorieForAgeRemoteKeyDao.deleteAll()
                    0
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val remoteKey = calorieForAgeRemoteKeyDao.getNextKey()
                    remoteKey?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val response = openDataService.getCalorieForAgeList(
                page = page,
                perPage = state.config.pageSize,
                serviceKey = appApplication.getOpenApiKey()
            )

            val list = response.body()?.data?.map { data ->
                data.toDomain()
            } ?: listOf()

            if (response.isSuccessful) {
                appDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        calorieForAgeDao.clearAll()
                    }
                    calorieForAgeDao.insertAll(list)
                    calorieForAgeRemoteKeyDao.insertOrReplace(CaloriesForAgeRemoteKey(nextPage = page + 1))
                }
                MediatorResult.Success(endOfPaginationReached = list.isEmpty())
            } else {
                MediatorResult.Error(Exception("${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw UnknownHostException()
            } else {
                MediatorResult.Error(e)
            }
        }
    }
} 