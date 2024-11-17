package dev.kick.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.model.PersonType
import dev.kick.presentation.R
import dev.kick.presentation.style.TextStyleEnum
import dev.kick.presentation.style.typography
import dev.kick.presentation.util.random

@Composable
fun CaloriesForAgeItem(
    modifier: Modifier = Modifier,
    data: CalorieForAge,
    onClickDetail: () -> Unit,
    onClickLike: () -> Unit,
) {
    val randomColor = remember { Color.Companion.random() }

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
        border = BorderStroke(1.dp, colorResource(id = R.color.black).copy(0.3f)),
        onClick = { onClickDetail() }
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Row {
                Box {
                    Text(
                        text = "${data.age}세",
                        textAlign = TextAlign.Center,
                        style = typography(TextStyleEnum.HeadLine1),
                        modifier = Modifier
                            .background(
                                color = randomColor,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(25.dp)
                    )

                    if (data.classificationPersonType != PersonType.CHILD) {
                        Image(
                            painter = painterResource(
                                if (data.classificationPersonType == PersonType.MALE)
                                    R.drawable.icon_female
                                else
                                    R.drawable.icon_male
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
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("권장 칼로리", style = typography(TextStyleEnum.Title2))
                            Text("${data.calories} 칼로리", style = typography(TextStyleEnum.Body2))
                        }

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("권장 단백질", style = typography(TextStyleEnum.Title2))
                            Text("${data.protein}g", style = typography(TextStyleEnum.Body2))
                        }
                    }
                    Spacer(Modifier.padding(vertical = 5.dp))
                    Row {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("권장 칼슘", style = typography(TextStyleEnum.Title2))
                            Text("${data.protein}g", style = typography(TextStyleEnum.Body2))
                        }

                        Image(
                            painter = painterResource(if (data.isLike) R.drawable.icon_like else R.drawable.icon_dislike),
                            contentDescription = "icon like",
                            modifier = Modifier
                                .clickable { onClickLike() }
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
    CaloriesForAgeItem(
        data = CalorieForAge(
            id = 6109,
            age = 22,
            calories = 8856,
            protein = 5404,
            calcium = 7937,
            vitaminA = 4.5,
            vitaminB1 = 6.7,
            vitaminB2 = 9276,
            classificationPersonType = PersonType.MALE,
            genderClassification = 7580,
            otherObesityCheckItems = 9389
        ),
        onClickDetail = {},
        onClickLike = {}
    )
}