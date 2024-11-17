package dev.kick.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.kick.presentation.R
import dev.kick.presentation.style.TextStyleEnum
import dev.kick.presentation.style.typography

@Composable
fun TextFieldSearchBox(
    modifier: Modifier = Modifier,
    text: String,
    textChange: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(44.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(20.dp)
            )
            .border(1.dp, colorResource(id = R.color.black), RoundedCornerShape(20.dp))
    ) {
        BasicTextField(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 4.dp)
                .weight(1f),
            value = text,
            onValueChange = textChange,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(R.string.textfield_hint),
                            color = colorResource(id = R.color.gray),
                            style = typography(TextStyleEnum.Body2),
                        )
                    }
                    innerTextField()
                }
            },
            singleLine = true,
            textStyle = typography(TextStyleEnum.Body2),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Number,
            ),
        )
    }
}