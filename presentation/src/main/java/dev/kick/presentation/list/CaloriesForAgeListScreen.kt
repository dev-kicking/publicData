package dev.kick.presentation.list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import dev.kick.domain.model.CalorieForAge
import dev.kick.presentation.R
import dev.kick.presentation.component.CaloriesForAgeItem
import dev.kick.presentation.component.ErrorScreen
import dev.kick.presentation.component.LoadingScreen
import dev.kick.presentation.component.TextFieldSearchBox
import dev.kick.presentation.component.TopTitleBar
import dev.kick.presentation.model.ListUiEffect
import dev.kick.presentation.model.ListUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CaloriesForAgeListScreen(
    modifier: Modifier = Modifier,
    viewModel: CaloriesForAgeListViewModel = hiltViewModel(),
    onClickDetail: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.uiEffect) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is ListUiEffect.NetworkError -> Toast.makeText(
                    context,
                    effect.message,
                    Toast.LENGTH_SHORT
                ).show()

                is ListUiEffect.ToastScrap -> Toast.makeText(
                    context,
                    context.getString(if (effect.isScrap) R.string.add_scrap_toast else R.string.delete_scrap_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Column {
        TopTitleBar(
            title = "연령별 권장 영양소 섭취량",
            onClick = null
        )

        when (uiState) {
            ListUiState.Error -> ErrorScreen(Modifier.fillMaxSize())
            ListUiState.Loading -> LoadingScreen(Modifier.fillMaxSize())
            is ListUiState.Success -> {
                val value = (uiState as ListUiState.Success)

                val list = value.caloriesForAgeList.collectAsLazyPagingItems()
                CaloriesForAgeListSuccessScreen(
                    modifier = modifier
                        .fillMaxSize(),
                    list = list,
                    text = value.text,
                    onClickDetail = onClickDetail,
                    onTextChange = viewModel::onTextChange
                )
            }
        }
    }
}

@Composable
fun CaloriesForAgeListSuccessScreen(
    modifier: Modifier,
    list: LazyPagingItems<CalorieForAge>,
    text: String,
    onClickDetail: (Int) -> Unit,
    onTextChange: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (list.loadState.refresh) {
            is LoadState.Loading -> LoadingScreen(Modifier.fillMaxSize())
            is LoadState.Error -> ErrorScreen(Modifier.fillMaxSize())

            is LoadState.NotLoading -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 10.dp),
                ) {
                    items(
                        count = list.itemCount,
                        key = list.itemKey { it.id }
                    ) { index ->
                        list[index]?.let {
                            CaloriesForAgeItem(
                                data = it,
                                onClickDetail = onClickDetail
                            )
                        }
                    }
                }

            }
        }

        TextFieldSearchBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter),
            text = text,
            textChange = onTextChange
        )
    }
}
