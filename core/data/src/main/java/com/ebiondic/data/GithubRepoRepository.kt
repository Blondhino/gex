package com.ebiondic.data

import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.dto.GithubRepositoryDetailsDto
import com.ebiondic.model.enum.SortCategory
import com.ebiondic.model.enum.SortDirection

interface GithubRepoRepository {
  suspend fun searchGithubRepository(
    repositoryName: String,
    sortCategory: String = SortCategory.NONE.apiName,
    sortDirection: String = SortDirection.DESCENDING.apiName,
    page: Int = 0
  ): Result<List<GithubRepoDto>>
  
  suspend fun getGithubRepositoryDetails(repositoryName: String, ownerName: String): Result<GithubRepositoryDetailsDto>
}