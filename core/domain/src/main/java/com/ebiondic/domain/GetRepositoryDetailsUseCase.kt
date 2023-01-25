package com.ebiondic.domain

import com.ebiondic.data.GithubRepoRepository
import com.ebiondic.model.convert.mapToGithubRepositoryDetails
import com.ebiondic.model.ui.GithubRepoDetails
import com.ebiondic.model.utils.unknownError
import javax.inject.Inject

class GetRepositoryDetailsUseCase @Inject constructor(
  private val githubRepoRepository: GithubRepoRepository
) {
  suspend operator fun invoke(
   repositoryName : String,
   ownerName : String,
  ): Result<GithubRepoDetails>{
    githubRepoRepository.getGithubRepositoryDetails(repositoryName, ownerName)
      .onSuccess {
        return Result.success(it.mapToGithubRepositoryDetails())
      }
      .onFailure {
        return Result.failure(it)
      }
    return unknownError()
  }
}