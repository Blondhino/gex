package com.ebiondic.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebiondic.common.SEARCH_DEBOUNCE_MILLIS
import com.ebiondic.designsystem.component.ASCENDING
import com.ebiondic.designsystem.component.DESCENDING
import com.ebiondic.domain.GetNavigationArgumentsForDetailsUseCase
import com.ebiondic.domain.GetRepositorySearchResultsUseCase
import com.ebiondic.model.enum.SortCategory
import com.ebiondic.model.enum.SortDirection
import com.ebiondic.model.exceptions.EndReached
import com.ebiondic.model.exceptions.NoResultsFound
import com.ebiondic.model.navigation.NavigateToDetailsArguments
import com.ebiondic.search.action.SearchScreenEvent
import com.ebiondic.search.action.SearchScreenEvent.*
import com.ebiondic.search.action.SearchScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val getRepositorySearchResults: GetRepositorySearchResultsUseCase,
  private val getNavigationArgumentsForDetails: GetNavigationArgumentsForDetailsUseCase
) : ViewModel() {
  var uiState by mutableStateOf(SearchScreenUiState())
  private var searchJob: Job? = null
  
  fun onEvent(event: SearchScreenEvent) {
    when (event) {
      is SearchTermChanged -> {
        uiState = uiState.copy(searchTerm = event.term)
        performSearch()
      }
      is SortCategoryClicked -> {
        uiState = uiState.copy(activeSortCategory = event.sortCategory)
        performSearch()
      }
      is OnSortDirectionClicked -> {
        uiState = uiState.copy(sortDirection = if (uiState.sortDirection == ASCENDING) DESCENDING else ASCENDING)
        performSearch()
      }
      is OnLoadMoreData -> {
        performSearch(isNewSearch = false)
      }
      is OnRefresh -> {
        uiState = uiState.copy(isRefreshingIndicatorVisible = true)
        performSearch()
      }
    }
  }
  
  fun getNavArgumentsForDetails(repositoryId: Int): NavigateToDetailsArguments {
    return getNavigationArgumentsForDetails(
      repositories = uiState.repositories,
      repositoryId = repositoryId
    )
  }
  
  private fun performSearch(isNewSearch: Boolean = true) {
    searchJob?.cancel()
    searchJob = viewModelScope.launch {
      if (uiState.searchTerm.isNotEmpty()) {
        delay(SEARCH_DEBOUNCE_MILLIS)
        uiState = uiState.copy(
          isLoading = isNewSearch,
          isFetchingInProgress = true,
          isRefreshingIndicatorVisible = uiState.isRefreshingIndicatorVisible
        )
        getRepositorySearchResults(
          repositoryName = uiState.searchTerm,
          sortCategory = SortCategory.fromCode(uiState.activeSortCategory),
          sortDirection = SortDirection.fromCode(uiState.sortDirection),
          isNewSearch = isNewSearch
        )
          .onSuccess {
            uiState = uiState.copy(
              repositories = it,
              isEndReached = false,
              noResultsFound = false,
              isSearchEmpty = false
            )
          }
          .onFailure {
            when (it) {
              is EndReached -> {
                uiState = uiState.copy(isEndReached = true)
              }
              is NoResultsFound -> {
                uiState = uiState.copy(noResultsFound = true, isSearchEmpty = false)
              }
              else -> {
                showError(it.message.orEmpty())
              }
            }
          }
        uiState = uiState.copy(isLoading = false, isRefreshingIndicatorVisible = false, isFetchingInProgress = false)
      } else {
        uiState = uiState.copy(
          isSearchEmpty = true,
          repositories = listOf(),
          noResultsFound = false,
        )
      }
    }
  }
  
  private fun showError(error: String) = viewModelScope.launch {
    uiState = uiState.copy(screenError = error)
    delay(100)
    uiState = uiState.copy(screenError = "")
  }
}

