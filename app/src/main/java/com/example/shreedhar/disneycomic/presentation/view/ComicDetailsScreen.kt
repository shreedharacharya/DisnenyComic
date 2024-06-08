package com.example.shreedhar.disneycomic.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shreedhar.disneycomic.base.BaseViewState
import com.example.shreedhar.disneycomic.extension.cast
import com.example.shreedhar.disneycomic.presentation.ComicDetailEvent
import com.example.shreedhar.disneycomic.presentation.ComicDetailViewState
import com.example.shreedhar.disneycomic.presentation.ComicDetailsViewModel

@Composable
fun ComicDetailsScreen(
    id: Int,
    viewModel: ComicDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    ComicDetailsBody {
        when (uiState) {
            is BaseViewState.Data -> ComicDetailsContent(
                dto = uiState.cast<BaseViewState.Data<ComicDetailViewState>>().value.comicDto,
            )
            is BaseViewState.Error -> EmptyView()
            is BaseViewState.Empty -> EmptyView(text = "No data")
            is BaseViewState.Loading -> LoadingView()
        }
    }

    LaunchedEffect(key1 = viewModel) {
        handleComicDetailsUIEvent(ComicDetailEvent.LoadDetail(id), viewModel)
    }
}

@Composable
private fun ComicDetailsBody(
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize().background(Color.Cyan),
        content = { pageContent(it) }
    )
}


fun handleComicDetailsUIEvent(
    uiEvent: ComicDetailEvent,
    viewModel: ComicDetailsViewModel
) {
    viewModel.onTriggerEvent(uiEvent)
}