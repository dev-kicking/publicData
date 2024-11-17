package dev.kick.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.model.PersonType
import dev.kick.presentation.R

@Composable
fun CaloriesForAgeItem(
    modifier: Modifier = Modifier,
    data: CalorieForAge,
    onClickDetail: (Int) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .heightIn()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = colorResource(id = R.color.black).copy(0.3f)
            ),
        border = BorderStroke(1.dp, colorResource(id = R.color.black)),
        onClick = { onClickDetail(data.id) }
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .heightIn(max = 100.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    SubcomposeAsyncImage(
                        model = data.imageUrl,
                        contentDescription = "random Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .heightIn(max = 100.dp)
                            .aspectRatio(1f),
                    ) {
                        val state by painter.state.collectAsStateWithLifecycle()
                        when (state) {
                            AsyncImagePainter.State.Empty -> {}
                            is AsyncImagePainter.State.Error -> ErrorButton { painter.restart() }
                            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
                        }
                    }

                    if (data.classificationPersonType != PersonType.CHILD) {
                        Image(
                            painter = painterResource(
                                if (data.classificationPersonType == PersonType.MALE)
                                    R.drawable.icon_male
                                else
                                    R.drawable.icon_female
                            ),
                            contentDescription = "gender icon",
                            modifier = Modifier
                                .padding(top = 2.dp, start = 2.dp)
                                .background(
                                    color = colorResource(R.color.gray).copy(0.5f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .align(Alignment.TopStart)
                        )
                    }
                }

                Spacer(Modifier.padding(horizontal = 5.dp))

                Column {
                    Row {
                        InfoItem(
                            modifier = Modifier.weight(1f),
                            label = stringResource(R.string.age),
                            value = "${data.age}세"
                        )
                        InfoItem(
                            modifier = Modifier.weight(1f),
                            label = stringResource(R.string.recommended_calories),
                            value = "${data.calories}cal"
                        )
                    }

                    Spacer(Modifier.padding(vertical = 10.dp))

                    Row {
                        InfoItem(
                            modifier = Modifier.weight(1f),
                            label = stringResource(R.string.recommended_calcium),
                            value = "${data.calcium}mg"
                        )
                        InfoItem(
                            modifier = Modifier.weight(1f),
                            label = stringResource(R.string.recommended_protein),
                            value = "${data.protein}g"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCaloriesForAgeItem() {
    val id = 12
    CaloriesForAgeItem(
        data = CalorieForAge(
            id = id,
            age = 22,
            calories = 8856,
            protein = 5404,
            calcium = 7937,
            vitaminA = 4.5,
            vitaminB1 = 6.7,
            vitaminB2 = 9276,
            classificationPersonType = PersonType.MALE,
            genderClassification = 7580,
            otherObesityCheckItems = 9389,
            imageUrl = "https://picsum.photos/${id}/200"
        ),
        onClickDetail = {}
    )
}