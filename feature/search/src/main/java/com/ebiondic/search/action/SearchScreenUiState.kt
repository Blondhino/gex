package com.ebiondic.search.action

import com.ebiondic.designsystem.component.SortCategory
import com.ebiondic.designsystem.component.SortDirection
import com.ebiondic.model.ui.GithubRepo

data class SearchScreenUiState(
  val searchTerm: String = "",
  val isLoading: Boolean = false,
  val screenError: String = "",
  val repositories: List<GithubRepo> = listOf(),
  val activeSortCategory: SortCategory = SortCategory.NONE,
  val sortDirection: SortDirection = SortDirection.DESCENDING
)
