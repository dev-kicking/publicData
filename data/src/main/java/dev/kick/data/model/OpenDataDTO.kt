package dev.kick.data.model

import com.google.gson.annotations.SerializedName

data class CalorieForAgeMetaDataResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("perPage") val perPage: Int,
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("currentCount") val currentCount: Int,
    @SerializedName("matchCount") val matchCount: Int,
    @SerializedName("data") val data: List<Data>
) {
    data class Data(
        @SerializedName("연번") val id: Int,
        @SerializedName("연령") val age: Int,
        @SerializedName("열량") val calories: Int,
        @SerializedName("단백질") val protein: Int,
        @SerializedName("칼슘") val calcium: Int,
        @SerializedName("비타민(A)") val vitaminA: String,
        @SerializedName("비타민(B1)") val vitaminB1: String,
        @SerializedName("비타민(B2)") val vitaminB2: Int,
        @SerializedName("분류(X)") val classificationX: String,
        @SerializedName("남녀구분") val genderClassification: Int,
        @SerializedName("그외 비만체크사항") val otherObesityCheckItems: Int
    )
}