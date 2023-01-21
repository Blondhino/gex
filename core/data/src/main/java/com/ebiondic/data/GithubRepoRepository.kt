package com.ebiondic.data

import com.ebiondic.model.dto.GithubRepoDto

interface GithubRepoRepository {
  suspend fun searchGithubRepository(repositoryName: String): Result<List<GithubRepoDto>>
}