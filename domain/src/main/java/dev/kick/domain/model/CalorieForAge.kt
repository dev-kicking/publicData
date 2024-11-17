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
    val vitaminA: Double,
    val vitaminB1: Double,
    val vitaminB2: Int,
    val classificationPersonType: PersonType,
    val genderClassification: Int,
    val otherObesityCheckItems: Int,
    val isLike: Boolean = false
)

enum class PersonType{
    MALE, FEMALE, CHILD
}