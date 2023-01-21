package com.ebiondic.search.action

data class SearchScreenUiState(
  val searchTerm: String = "",
  val isLoading: Boolean = false,
  val screenError: String = ""
)
