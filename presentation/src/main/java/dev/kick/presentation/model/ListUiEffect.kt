package dev.kick.presentation.model

sealed interface ListUiEffect {
    data class ToastScrap(val isScrap: Boolean): ListUiEffect
    data class NetworkError(val message: String): ListUiEffect
}