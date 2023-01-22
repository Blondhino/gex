package com.ebiondic.domain

import com.ebiondic.data.GithubRepoRepository
import com.ebiondic.model.convert.mapToListOfGithubRepositories
import com.ebiondic.model.enum.SortCategory
import com.ebiondic.model.enum.SortDirection
import com.ebiondic.model.ui.GithubRepo
import com.ebiondic.model.utils.unknownError
import javax.inject.Inject

class GetRepositorySearchResultsUseCase @Inject constructor(
  private val githubRepoRepository: GithubRepoRepository
) {
  suspend operator fun invoke(
    repositoryName: String,
    sortCategory: SortCategory = SortCategory.NONE,
    sortDirection: SortDirection = SortDirection.DESCENDING
  ): Result<List<GithubRepo>> {
    githubRepoRepository.searchGithubRepository(repositoryName, sortCategory.apiName, sortDirection.apiName)
      .onSuccess {
        return Result.success(it.mapToListOfGithubRepositories())
      }
      .onFailure {
        return Result.failure(it)
      }
    return unknownError()
  }
}