package com.ebiondic.data

import com.ebiondic.model.convert.mapToGithubRepositoryDetailsDto
import com.ebiondic.model.convert.mapToListOfGithubRepositoryDtos
import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.dto.GithubRepositoryDetailsDto
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
    sortDirection: String,
    page: Int
  ): Result<List<GithubRepoDto>> {
    safeApiCall { api.searchRepositories(repositoryName, sortCategory, sortDirection, page) }
      .onSuccess {
        return Result.success(it.mapToListOfGithubRepositoryDtos())
      }
      .onFailure {
        return Result.failure(it)
      }
    return unknownError()
  }
  
  override suspend fun getGithubRepositoryDetails(
    repositoryName: String,
    ownerName: String
  ): Result<GithubRepositoryDetailsDto> {
    safeApiCall { api.getGithubRepositoryDetails(repositoryName, ownerName) }
      .onSuccess {
        return Result.success(it.mapToGithubRepositoryDetailsDto())
      }
      .onFailure {
        return Result.failure(it)
      }
    return unknownError()
  }
  
}