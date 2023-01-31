package com.ebiondic.testing.repository

import com.ebiondic.data.GithubRepoRepository
import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.dto.GithubRepositoryDetailsDto
import com.ebiondic.model.utils.unknownError

class TestGithubRepoRepository : GithubRepoRepository {
  
  override suspend fun searchGithubRepository(
    repositoryName: String,
    sortCategory: String,
    sortDirection: String,
    page: Int
  ): Result<List<GithubRepoDto>> {
  return unknownError()
  }
  
  override suspend fun getGithubRepositoryDetails(repositoryName: String, ownerName: String): Result<GithubRepositoryDetailsDto> {
    return unknownError()
  }
}