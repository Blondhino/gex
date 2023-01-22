package com.ebiondic.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebiondic.designsystem.component.SortCategory
import com.ebiondic.designsystem.component.SortDirection
import com.ebiondic.domain.GetRepositorySearchResultsUseCase
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
  private val getRepositorySearchResults: GetRepositorySearchResultsUseCase
) : ViewModel() {
  var uiState by mutableStateOf(SearchScreenUiState())
  private var searchJob: Job? = null
  
  fun onEvent(event: SearchScreenEvent) {
    when (event) {
      is SearchTermChanged -> {
        uiState = uiState.copy(searchTerm = event.term)
        performSearch(uiState.searchTerm)
      }
      is SortCategoryClicked -> {
        updateActiveSortCategory(event.category)
      }
      is OnSortDirectionClicked -> {
        updateSortDirection()
      }
    }
  }
  
  private fun updateSortDirection() {
    val newSortDirection =
      if (uiState.sortDirection == SortDirection.ASCENDING) SortDirection.DESCENDING else SortDirection.ASCENDING
    uiState = uiState.copy(sortDirection = newSortDirection)
  }
  
  private fun updateActiveSortCategory(sortCategory: SortCategory) {
    val newSortingCategory = if (uiState.activeSortCategory == sortCategory) SortCategory.NONE else sortCategory
    uiState = uiState.copy(activeSortCategory = newSortingCategory)
  }
  
  private fun performSearch(searchTerm: String) {
    searchJob?.cancel()
    searchJob = viewModelScope.launch {
      delay(500)
      uiState = uiState.copy(isLoading = true)
      getRepositorySearchResults(searchTerm)
        .onSuccess { uiState = uiState.copy(repositories = it) }
        .onFailure { }
      uiState = uiState.copy(isLoading = false)
    }
  }
}

