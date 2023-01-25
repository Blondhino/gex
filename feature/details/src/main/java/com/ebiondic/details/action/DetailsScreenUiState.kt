package com.ebiondic.details.action

import com.ebiondic.model.ui.GithubRepoDetails

data class DetailsScreenUiState(
  val isLoading: Boolean = false,
  val details: GithubRepoDetails? = null
)
