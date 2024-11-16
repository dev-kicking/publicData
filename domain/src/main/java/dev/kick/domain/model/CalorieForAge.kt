package dev.kick.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calorieForAge")
data class CalorieForAge(
    @PrimaryKey val id: Int,
    val age: Int,
    val calories: Int,
    val protein: Int,
    val calcium: Int,
    val vitaminA: String,
    val vitaminB1: String,
    val vitaminB2: Int,
    val classificationX: String,
    val genderClassification: Int,
    val otherObesityCheckItems: Int
)
