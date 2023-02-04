package com.ebiondic.search.action

import com.ebiondic.designsystem.component.DESCENDING
import com.ebiondic.designsystem.component.STARS
import com.ebiondic.model.ui.GithubRepo

data class SearchScreenUiState(
  val searchTerm: String = "",
  val isLoading: Boolean = false,
  val isFetchingInProgress: Boolean = false,
  val isRefreshingIndicatorVisible: Boolean = false,
  val screenError: String = "",
  val repositories: List<GithubRepo> = listOf(),
  val isEndReached: Boolean = false,
  val noResultsFound: Boolean = false,
  val isSearchEmpty: Boolean = true,
  val activeSortCategory: Int = STARS,
  val sortDirection: Int = DESCENDING
)
