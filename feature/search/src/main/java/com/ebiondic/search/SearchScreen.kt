package com.ebiondic.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.designsystem.component.PaginatedRefreshableRail
import com.ebiondic.designsystem.component.RepositoryItem
import com.ebiondic.designsystem.component.SearchAndSort
import com.ebiondic.designsystem.theme.mediumPadding
import com.ebiondic.search.action.SearchScreenEvent
import com.ebiondic.search.action.SearchScreenUiState

@Composable
internal fun SearchRoute(
  modifier: Modifier = Modifier,
  viewModel: SearchViewModel = hiltViewModel(),
  onRepositorySelected: (repositoryName: String, ownerName: String) -> Unit
) {
  SearchScreen(
    modifier = modifier,
    uiState = viewModel.uiState,
    onSearchTermChanged = { viewModel.onEvent(SearchScreenEvent.SearchTermChanged(it)) },
    onSortCategorySelected = { viewModel.onEvent(SearchScreenEvent.SortCategoryClicked(it)) },
    onSorDirectionClicked = { viewModel.onEvent(SearchScreenEvent.OnSortDirectionClicked) },
    onRepositorySelected = {
      val arguments = viewModel.getNavArgumentsForDetails(it)
      onRepositorySelected(arguments.repositoryName, arguments.ownerName)
    }
  )
}

@Composable
internal fun SearchScreen(
  modifier: Modifier,
  uiState: SearchScreenUiState,
  onSearchTermChanged: (String) -> Unit,
  onSortCategorySelected: (Int) -> Unit = { },
  onSorDirectionClicked: () -> Unit = {},
  onRepositorySelected: (id: Int) -> Unit
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .padding(mediumPadding)
  ) {
    PaginatedRefreshableRail(
      isFetching = uiState.isLoading,
      loadMoreData = { },
      items = uiState.repositories,
      onRefresh = { },
      header = {
        SearchAndSort(
          searchTerm = uiState.searchTerm,
          activeSortCategory = uiState.activeSortCategory,
          sortDirection = uiState.sortDirection,
          onSearchTermChanged = { onSearchTermChanged(it) },
          onSortCategorySelected = { onSortCategorySelected(it) },
          onSortDirectionClicked = { onSorDirectionClicked() }
        )
      }
    ) {
      RepositoryItem(
        repositoryId = it.repositoryId,
        repositoryName = it.repositoryName,
        authorName = it.authorName,
        authorThumbnailImageUrl = it.authorThumbnailImageUrl,
        numberOfWatchers = it.numberOfWatchers,
        numberOfForks = it.numberOfForks,
        numberOfIssues = it.numberOfIssues,
        onItemClicked = { repositoryId -> onRepositorySelected(repositoryId) }
      )
    }
  }
}