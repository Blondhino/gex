package com.ebiondic.domain

import com.ebiondic.model.navigation.NavigateToDetailsArguments
import com.ebiondic.model.ui.GithubRepo
import javax.inject.Inject

class GetNavigationArgumentsForDetailsUseCase @Inject constructor() {
  operator fun invoke(
    repositories: List<GithubRepo>,
    repositoryId: Int
  ): NavigateToDetailsArguments {
    val selectedRepository = repositories.firstOrNull { it.repositoryId == repositoryId }
    return NavigateToDetailsArguments(
      repositoryName = selectedRepository?.repositoryName ?: "",
      ownerName = selectedRepository?.authorName ?: ""
    )
  }
}