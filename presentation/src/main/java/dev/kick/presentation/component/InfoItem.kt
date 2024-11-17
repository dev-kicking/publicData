package dev.kick.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.kick.presentation.style.TextStyleEnum
import dev.kick.presentation.style.typography

@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(label, style = typography(TextStyleEnum.Title2))
        Text(value, style = typography(TextStyleEnum.Body2))
    }
}