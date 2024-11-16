package dev.kick.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.kick.domain.model.CalorieForAge

@Database(entities = [CalorieForAge::class, CaloriesForAgeRemoteKey::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun calorieForAgeDao(): CalorieForAgeDao
    abstract fun caloriesForAgeRemoteKeyDao(): CaloriesForAgeRemoteKeyDao
}