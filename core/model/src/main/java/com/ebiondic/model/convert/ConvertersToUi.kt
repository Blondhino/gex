package com.ebiondic.model.convert

import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.response.GithubRepositorySearchResponse

fun GithubRepositorySearchResponse.mapToListOfGithubRepositoryDtos(): List<GithubRepoDto> {
  return this.items.map {
    GithubRepoDto(
      repositoryName = it.name.orEmpty(),
      authorName = "@"+it.owner?.login.orEmpty(),
      authorThumbnailImageUrl = it.owner?.avatar_url.orEmpty(),
      numberOfWatchers = it.watchers_count ?: 0,
      numberOfForks = it.forks_count ?: 0,
      numberOfIssues = it.open_issues_count ?: 0
    )
  }
}