package dev.kick.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import dev.kick.domain.model.CalorieForAge
import dev.kick.domain.model.PersonType
import dev.kick.presentation.R
import dev.kick.presentation.component.ErrorButton
import dev.kick.presentation.component.ErrorScreen
import dev.kick.presentation.component.InfoItem
import dev.kick.presentation.component.LoadingScreen
import dev.kick.presentation.component.TopTitleBar
import dev.kick.presentation.model.CaloriesForAgeDetailUiEffect
import dev.kick.presentation.model.CaloriesForAgeDetailUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CaloriesForAgeDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CaloriesForAgeDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(viewModel.uiEffect) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is CaloriesForAgeDetailUiEffect.NotFoundDetail -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.not_found_detail),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Column {
        TopTitleBar(
            title = stringResource(R.string.detail_screen_title),
            onClick = onBackClick
        )

        when (uiState) {
            CaloriesForAgeDetailUiState.Error -> ErrorScreen(modifier.fillMaxSize())
            CaloriesForAgeDetailUiState.Loading -> LoadingScreen(modifier.fillMaxSize())
            is CaloriesForAgeDetailUiState.Success -> {
                val value = (uiState as CaloriesForAgeDetailUiState.Success)
                CaloriesForAgeDetailSuccessScreen(
                    data = value.data
                )
            }
        }
    }
}

@Composable
fun CaloriesForAgeDetailSuccessScreen(
    data: CalorieForAge,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
        ) {
            SubcomposeAsyncImage(
                model = data.imageUrl,
                contentDescription = "random Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
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

        Spacer(Modifier.padding(vertical = 10.dp))

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
        InfoItem(
            modifier = Modifier.weight(1f),
            label = stringResource(R.string.vitamin_a),
            value = "${data.vitaminA}g"
        )
        InfoItem(
            modifier = Modifier.weight(1f),
            label = stringResource(R.string.vitamin_b1),
            value = "${data.vitaminB1}g"
        )
        InfoItem(
            modifier = Modifier.weight(1f),
            label = stringResource(R.string.vitamin_b2),
            value = "${data.vitaminB2}g"
        )
    }
}

@Preview
@Composable
private fun PreviewCaloriesForAgeDetailSuccessScreen() {
    CaloriesForAgeDetailSuccessScreen(
        data = CalorieForAge(
            id = 4480,
            age = 3266,
            calories = 5220,
            protein = 3226,
            calcium = 3064,
            vitaminA = 4.5,
            vitaminB1 = 6.7,
            vitaminB2 = 6864,
            classificationPersonType = PersonType.MALE,
            genderClassification = 6601,
            otherObesityCheckItems = 4608,
            imageUrl = "http://www.bing.com/search?q=doctus"
        )
    )
}