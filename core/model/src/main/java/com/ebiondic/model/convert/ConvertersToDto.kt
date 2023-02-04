package com.ebiondic.model.convert

import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.dto.GithubRepositoryDetailsDto
import com.ebiondic.model.response.GithubRepositoryDetailsResponse
import com.ebiondic.model.response.GithubRepositorySearchResponse

fun GithubRepositorySearchResponse.mapToListOfGithubRepositoryDtos(): List<GithubRepoDto> {
  return this.items.map {
    GithubRepoDto(
      repositoryId = it.id ?: 0,
      repositoryName = it.name.orEmpty(),
      authorName = it.owner?.login.orEmpty(),
      authorThumbnailImageUrl = it.owner?.avatar_url.orEmpty(),
      authorOnlineProfileUrl = it.owner?.html_url.orEmpty(),
      numberOfWatchers = it.watchers_count ?: 0,
      numberOfForks = it.forks_count ?: 0,
      numberOfIssues = it.open_issues_count ?: 0
    )
  }
}

fun GithubRepositoryDetailsResponse.mapToGithubRepositoryDetailsDto(): GithubRepositoryDetailsDto {
  return GithubRepositoryDetailsDto(
    name = this.name.orEmpty(),
    authorId = this.owner?.id ?: 0,
    authorName = this.owner?.login.orEmpty(),
    authorThumbnailImageUrl = this.owner?.avatar_url.orEmpty(),
    authorOnlineProfileUrl = this.owner?.html_url.orEmpty(),
    repositoryDescription = this.description.orEmpty(),
    repositoryCreationDate = this.created_at.orEmpty(),
    repositoryLastModificationDate = this.updated_at.orEmpty(),
    language = this.language.orEmpty(),
    repositoryOnlineDetails = this.html_url.orEmpty(),
    forks = this.forks_count ?: 0,
    watchers = this.watchers_count ?: 0,
    issues = this.open_issues_count ?: 0,
    stars = this.stargazers_count ?: 0
  )
}
