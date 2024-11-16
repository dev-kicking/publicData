package dev.kick.data.mapper

import dev.kick.data.model.CalorieForAgeMetaDataResponse
import dev.kick.domain.model.CalorieForAge

internal fun CalorieForAgeMetaDataResponse.Data.toDomain() = CalorieForAge(
    id = id,
    age = age,
    calories = calories,
    protein = protein,
    calcium = calcium,
    vitaminA = vitaminA,
    vitaminB1 = vitaminB1,
    vitaminB2 = vitaminB2,
    classificationX = classificationX,
    genderClassification = genderClassification,
    otherObesityCheckItems = otherObesityCheckItems
)