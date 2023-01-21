package com.ebiondic.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.designsystem.component.InputTextField
import com.ebiondic.designsystem.component.PaginatedRefreshableRail
import com.ebiondic.designsystem.component.RepositoryItem
import com.ebiondic.designsystem.theme.mediumPadding
import com.ebiondic.designsystem.theme.smallPadding
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
  Column(modifier = modifier
    .fillMaxSize()
    .padding(mediumPadding)) {
    InputTextField(
      value = uiState.searchTerm,
      onValueChange = { onSearchTermChanged(it) },
      hint = stringResource(R.string.search_placeholder)
    )
    
    PaginatedRefreshableRail(
      isFetching = false,
      loadMoreData = {  },
      items = uiState.repositories,
      onRefresh = { }) {
      RepositoryItem(
        repositoryName = it.repositoryName,
        authorName = it.authorName,
        authorThumbnailImageUrl = it.authorThumbnailImageUrl,
        numberOfWatchers = it.numberOfWatchers,
        numberOfForks = it.numberOfForks,
        numberOfIssues = it.numberOfIssues
      )
    }
  }
}