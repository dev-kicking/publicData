package dev.kick.data.datasource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kick.domain.model.CalorieForAge

@Dao
interface CalorieForAgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(calorieForAge: List<CalorieForAge>)

    @Query("SELECT * FROM calorieForAge")
    fun getCalorieForAgePagingSource(): PagingSource<Int, CalorieForAge>

    @Query("SELECT * FROM calorieForAge")
    fun getCalorieForAgeList(): List<CalorieForAge>

    @Query("SELECT * FROM calorieForAge WHERE age=:age")
    fun findCalorieForAgePagingSource(age: Int): PagingSource<Int, CalorieForAge>

    @Query("SELECT * FROM calorieForAge WHERE id=:id")
    suspend fun getCalorieForAge(id: Int): CalorieForAge

    @Query("DELETE FROM calorieForAge")
    suspend fun clearAll()
}