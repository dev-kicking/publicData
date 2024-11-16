package dev.kick.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CaloriesForAgeRemoteKey(
    @PrimaryKey val nextPage: Int,
)
