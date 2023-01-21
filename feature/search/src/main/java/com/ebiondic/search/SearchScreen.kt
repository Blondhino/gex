package com.ebiondic.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.search.action.SearchScreenEvent
import com.ebiondic.search.action.SearchScreenUiState

@Composable
internal fun SearchRoute(
  modifier: Modifier = Modifier,
  viewModel: SearchViewModel = hiltViewModel(),
  onRepositorySelected: () -> Unit
) {
  SearchScreen(
    modifier = modifier,
    uiState = viewModel.uiState,
    onSearchTermChanged = { viewModel.onEvent(SearchScreenEvent.SearchTermChanged(it)) }
  ) {
  
  }
}

@Composable
internal fun SearchScreen(
  modifier: Modifier,
  uiState: SearchScreenUiState,
  onSearchTermChanged: (String) -> Unit,
  onRepositorySelected: () -> Unit
) {
  Box(modifier = modifier.fillMaxSize()) {
    Text("This is a search screen")
  }
}