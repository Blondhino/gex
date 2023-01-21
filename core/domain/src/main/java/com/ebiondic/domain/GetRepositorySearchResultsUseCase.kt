package com.ebiondic.domain

import com.ebiondic.data.GithubRepoRepository
import com.ebiondic.model.convert.mapToListOfGithubRepositories
import com.ebiondic.model.ui.GithubRepo
import com.ebiondic.model.utils.unknownError
import javax.inject.Inject

class GetRepositorySearchResultsUseCase @Inject constructor(
  private val githubRepoRepository: GithubRepoRepository
) {
  suspend operator fun invoke(repositoryName: String): Result<List<GithubRepo>> {
    githubRepoRepository.searchGithubRepository(repositoryName)
      .onSuccess {
        return Result.success(it.mapToListOfGithubRepositories())
      }
      .onFailure {
        return Result.failure(it)
      }
    return unknownError()
  }
}