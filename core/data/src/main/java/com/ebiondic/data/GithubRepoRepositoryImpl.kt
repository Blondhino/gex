package com.ebiondic.data

import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.network.GithubApi
import com.ebiondic.network.utils.SafeApiCall
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
  private val api: GithubApi,
  private val safeApiCall: SafeApiCall
) : GithubRepoRepository {
  
  override suspend fun searchGithubRepository(repositoryName: String): Result<List<GithubRepoDto>> {
    safeApiCall { api.searchRepositories(repositoryName) }
    return Result.success(listOf())
  }
  
}