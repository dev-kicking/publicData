package dev.kick.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CaloriesForAgeRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: CaloriesForAgeRemoteKey)

    @Query("SELECT * FROM caloriesForAgeRemoteKey ORDER BY nextPage DESC LIMIT 1")
    suspend fun getNextKey(): CaloriesForAgeRemoteKey?

    @Query("DELETE FROM caloriesForAgeRemoteKey")
    suspend fun deleteAll()
}