package dev.kick.presentation.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import dev.kick.presentation.model.ListUiEffect
import dev.kick.presentation.model.ListUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CaloriesForAgeListScreen(
    modifier: Modifier = Modifier,
    viewModel: CaloriesForAgeListViewModel = hiltViewModel(),
    onClickDetail: () -> Unit,
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
                onClickDetail = onClickDetail
            )
        }
    }
}

@Composable
fun CaloriesForAgeListSuccessScreen(
    modifier: Modifier,
    list: LazyPagingItems<CalorieForAge>,
    onClickDetail: () -> Unit,
) {
    if (list.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
        )
    }

    if (list.loadState.refresh is LoadState.NotLoading) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 10.dp),
        ) {
            item {
                // TODO: textField
            }

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
