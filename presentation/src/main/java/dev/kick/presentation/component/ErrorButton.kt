package dev.kick.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.kick.presentation.R

@Composable
fun ErrorButton(onClick: () -> Unit) {
    Image(
        painter = painterResource(R.drawable.ico_retry),
        contentDescription = "retry",
        modifier = Modifier.clickable { onClick() }
    )
}

@Preview
@Composable
private fun PreviewErrorButton() {
    ErrorButton {

    }
}