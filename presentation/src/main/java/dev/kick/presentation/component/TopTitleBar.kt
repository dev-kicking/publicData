package dev.kick.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.kick.presentation.R
import dev.kick.presentation.style.TextStyleEnum
import dev.kick.presentation.style.typography

@Composable
fun TopTitleBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onClick: (() -> Unit)?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        onClick?.let {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = "backIcon",
                modifier = Modifier
                    .clickable { onClick() }
                    .padding(10.dp)
            )
        }

        Spacer(Modifier.padding(horizontal = 10.dp))

        Text(text = title, style = typography(TextStyleEnum.Title1))
    }
}