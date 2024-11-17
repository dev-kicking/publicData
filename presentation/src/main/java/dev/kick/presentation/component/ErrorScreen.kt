package dev.kick.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.kick.presentation.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = stringResource(R.string.error_title)
        )
    }
}

@Preview
@Composable
private fun PreviewErrorLayout() {
    ErrorScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}