package dev.kick.data.service

import dev.kick.data.model.CalorieForAgeMetaDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDataService {
    @GET("/15050017/v1/uddi:9ab436e3-f169-49bf-8511-cb2e7a4ac314")
    suspend fun getCalorieForAgeList(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int = 10,
        @Query("serviceKey") serviceKey: String,
    ): Response<CalorieForAgeMetaDataResponse>
}