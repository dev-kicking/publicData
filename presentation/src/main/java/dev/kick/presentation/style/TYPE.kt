package dev.kick.presentation.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.kick.presentation.R

val fontFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal)
)

enum class TextStyleEnum {
    Title1, Title2, Title3, Title4,
    Body1, Body2, Body3,
    TextButton,
    HeadLine1,
    HeadLine2,
    HeadLine3,
    Caption,
    Button,
}

val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp

@Composable
fun typography(
    textStyle: TextStyleEnum,
): TextStyle = when (textStyle) {
    TextStyleEnum.Title1 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.nonScaledSp,
        lineHeight = 27.nonScaledSp
    )

    TextStyleEnum.Title2 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 17.nonScaledSp,
        lineHeight = 23.nonScaledSp
    )

    TextStyleEnum.Title3 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.nonScaledSp,
        lineHeight = 20.nonScaledSp,
    )

    TextStyleEnum.Title4 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.nonScaledSp,
        lineHeight = 16.nonScaledSp,
    )

    TextStyleEnum.Body1 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 17.nonScaledSp,
        lineHeight = 23.nonScaledSp,
    )

    TextStyleEnum.Body2 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.nonScaledSp,
        lineHeight = 22.nonScaledSp,
    )

    TextStyleEnum.Body3 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.nonScaledSp,
        lineHeight = 16.nonScaledSp,
    )

    TextStyleEnum.TextButton -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.nonScaledSp,
        lineHeight = 18.nonScaledSp
    )

    TextStyleEnum.HeadLine1 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 34.nonScaledSp,
        lineHeight = 46.nonScaledSp
    )

    TextStyleEnum.HeadLine2 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.nonScaledSp,
        lineHeight = 38.nonScaledSp
    )


    TextStyleEnum.HeadLine3 -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.nonScaledSp,
        lineHeight = 30.nonScaledSp
    )

    TextStyleEnum.Caption -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.nonScaledSp,
        lineHeight = 16.nonScaledSp
    )

    TextStyleEnum.Button -> TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.nonScaledSp,
        lineHeight = 22.nonScaledSp,
    )
}