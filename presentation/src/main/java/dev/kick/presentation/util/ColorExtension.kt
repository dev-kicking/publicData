package dev.kick.presentation.util

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

// 랜덤 칼라 가져오기
fun Color.Companion.random(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}