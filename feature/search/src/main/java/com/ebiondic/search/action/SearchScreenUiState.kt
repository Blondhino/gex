package com.ebiondic.search.action

import com.ebiondic.model.ui.GithubRepo

data class SearchScreenUiState(
  val searchTerm: String = "",
  val isLoading: Boolean = false,
  val screenError: String = "",
  val repositories : List<GithubRepo> = listOf()
)
