package com.ebiondic.data

import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.enum.SortCategory
import com.ebiondic.model.enum.SortDirection

interface GithubRepoRepository {
  suspend fun searchGithubRepository(
    repositoryName: String,
    sortCategory: String = SortCategory.NONE.apiName,
    sortDirection: String = SortDirection.DESCENDING.apiName
  ): Result<List<GithubRepoDto>>
}