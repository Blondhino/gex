package com.ebiondic.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebiondic.designsystem.component.ASCENDING
import com.ebiondic.designsystem.component.DESCENDING
import com.ebiondic.domain.GetRepositorySearchResultsUseCase
import com.ebiondic.model.enum.SortCategory
import com.ebiondic.model.enum.SortDirection
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
  private val getRepositorySearchResults: GetRepositorySearchResultsUseCase
) : ViewModel() {
  var uiState by mutableStateOf(SearchScreenUiState())
  private var searchJob: Job? = null
  
  fun onEvent(event: SearchScreenEvent) {
    when (event) {
      is SearchTermChanged -> {
        updateSearchField(event.term)
        performSearch()
      }
      is SortCategoryClicked -> {
        updateActiveSortCategory(event.sortCategory)
        performSearch()
      }
      is OnSortDirectionClicked -> {
        updateSortDirection()
        performSearch()
      }
    }
  }
  
  fun getNavArgumentsForDetails(repositoryId: Int): NavigateToDetailsArguments {
    val selectedRepository = uiState.repositories.firstOrNull { it.repositoryId == repositoryId }
    return NavigateToDetailsArguments(
      repositoryName = selectedRepository?.repositoryName ?: "",
      ownerName = selectedRepository?.authorName ?: ""
    )
  }
  
  private fun updateSearchField(term: String) {
    uiState = uiState.copy(searchTerm = term)
  }
  
  private fun updateSortDirection() {
    val newSortDirection =
      if (uiState.sortDirection == ASCENDING) DESCENDING else ASCENDING
    uiState = uiState.copy(sortDirection = newSortDirection)
  }
  
  private fun updateActiveSortCategory(sortCategory: Int) {
    uiState = uiState.copy(activeSortCategory = sortCategory)
  }
  
  private fun performSearch() {
    searchJob?.cancel()
    searchJob = viewModelScope.launch {
      delay(500)
      uiState = uiState.copy(isLoading = true)
      getRepositorySearchResults(
        repositoryName = uiState.searchTerm,
        sortDirection = SortDirection.fromCode(uiState.sortDirection),
        sortCategory = SortCategory.fromCode(uiState.activeSortCategory)
      )
        .onSuccess {
          uiState = uiState.copy(repositories = it)
        }
        .onFailure { }
      uiState = uiState.copy(isLoading = false)
    }
  }
}

