package com.ebiondic.data

import com.ebiondic.model.convert.mapToListOfGithubRepositoryDtos
import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.utils.SafeApiCall
import com.ebiondic.model.utils.unknownError
import com.ebiondic.network.GithubApi
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
  private val api: GithubApi,
  private val safeApiCall: SafeApiCall
) : GithubRepoRepository {
  
  override suspend fun searchGithubRepository(
    repositoryName: String,
    sortCategory: String,
    sortDirection: String
  ): Result<List<GithubRepoDto>> {
    safeApiCall { api.searchRepositories(repositoryName, sortCategory, sortDirection) }
      .onSuccess {
        return Result.success(it.mapToListOfGithubRepositoryDtos())
      }
      .onFailure {
        return Result.failure(it)
      }
    return unknownError()
  }
  
}