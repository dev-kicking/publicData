package dev.kick.data.mapper

import dev.kick.data.model.CalorieForAgeMetaDataResponse
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.model.PersonType

internal fun CalorieForAgeMetaDataResponse.Data.toDomain() = CalorieForAge(
    id = id,
    age = age,
    calories = calories,
    protein = protein,
    calcium = calcium,
    vitaminA = vitaminA.toDouble(),
    vitaminB1 = vitaminB1.toDouble(),
    vitaminB2 = vitaminB2,
    classificationPersonType = when (classificationPersonType) {
        "M" -> PersonType.MALE
        "F" -> PersonType.FEMALE
        else -> PersonType.CHILD
    },
    genderClassification = genderClassification,
    otherObesityCheckItems = otherObesityCheckItems
)