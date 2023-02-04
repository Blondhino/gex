package com.ebiondic.search

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.designsystem.R.drawable
import com.ebiondic.designsystem.component.*
import com.ebiondic.designsystem.theme.mediumPadding
import com.ebiondic.search.action.SearchScreenEvent
import com.ebiondic.search.action.SearchScreenUiState

@Composable
internal fun SearchRoute(
  modifier: Modifier = Modifier,
  viewModel: SearchViewModel = hiltViewModel(),
  onRepositorySelected: (repositoryName: String, ownerName: String) -> Unit
) {
  val screenError = viewModel.uiState.screenError
  val context = LocalContext.current
  SearchScreen(
    modifier = modifier,
    uiState = viewModel.uiState,
    onSearchTermChanged = { viewModel.onEvent(SearchScreenEvent.SearchTermChanged(it)) },
    onSortCategorySelected = { viewModel.onEvent(SearchScreenEvent.SortCategoryClicked(it)) },
    onSorDirectionClicked = { viewModel.onEvent(SearchScreenEvent.OnSortDirectionClicked) },
    onLoadMoreData = { viewModel.onEvent(SearchScreenEvent.OnLoadMoreData) },
    onRefresh = { viewModel.onEvent(SearchScreenEvent.OnRefresh) },
    onRepositorySelected = {
      val arguments = viewModel.getNavArgumentsForDetails(it)
      onRepositorySelected(arguments.repositoryName, arguments.ownerName)
    }
  )
  LaunchedEffect(key1 = screenError) {
    if (screenError.isNotEmpty()) {
      Toast.makeText(context, screenError, Toast.LENGTH_SHORT).show()
    }
  }
}

@Composable
internal fun SearchScreen(
  modifier: Modifier,
  uiState: SearchScreenUiState,
  onSearchTermChanged: (String) -> Unit,
  onSortCategorySelected: (Int) -> Unit = { },
  onSorDirectionClicked: () -> Unit = {},
  onRepositorySelected: (id: Int) -> Unit,
  onLoadMoreData: () -> Unit = {},
  onRefresh: () -> Unit = {},
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .padding(mediumPadding)
  ) {
    PaginatedRefreshableRail(
      isFetching = uiState.isFetchingInProgress,
      loadMoreData = { onLoadMoreData() },
      items = uiState.repositories,
      isEnabledScrollToTopButton = true,
      isRefreshIndicatorVisible = uiState.isRefreshingIndicatorVisible,
      isEndReached = uiState.isEndReached,
      onRefresh = { onRefresh() },
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
    if (uiState.noResultsFound) {
      Hint(
        modifier = Modifier.align(Alignment.Center),
        image = drawable.ic_no_results,
        message = R.string.no_results_found_message
      )
    }
    if (uiState.isSearchEmpty) {
      Hint(
        modifier = Modifier.align(Alignment.Center),
        image = drawable.ic_empty_search,
        message = R.string.empty_search_message
      )
    }
    LoadingIndicator(isLoading = uiState.isLoading && !uiState.isRefreshingIndicatorVisible)
  }
}