package com.ebiondic.details.action

import com.ebiondic.model.ui.GithubRepoDetails

data class DetailsScreenUiState(
  val isLoading: Boolean = true,
  val details: GithubRepoDetails? = null,
  val screenError: String = ""
)
