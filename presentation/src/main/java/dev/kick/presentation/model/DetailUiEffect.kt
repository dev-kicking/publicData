package dev.kick.presentation.model

sealed interface DetailUiEffect {
    data class ShowToast(
        val message: String
    ): DetailUiEffect

    data class ScrapToast(
        val isScrap: Boolean
    ): DetailUiEffect

    data class NetworkError(
        val message: String
    ): DetailUiEffect

    data class NotFoundDetail(
        val message: String,
    ): DetailUiEffect
}