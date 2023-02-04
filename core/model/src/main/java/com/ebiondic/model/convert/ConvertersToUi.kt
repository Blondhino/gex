package com.ebiondic.model.convert

import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.dto.GithubRepositoryDetailsDto
import com.ebiondic.model.ui.GithubRepo
import com.ebiondic.model.ui.GithubRepoDetails

fun List<GithubRepoDto>.mapToListOfGithubRepositories(): List<GithubRepo> {
  return this.map {
    GithubRepo(
      repositoryId = it.repositoryId,
      repositoryName = it.repositoryName,
      authorName = it.authorName,
      authorThumbnailImageUrl = it.authorThumbnailImageUrl,
      authorOnlineProfileUrl = it.authorOnlineProfileUrl,
      numberOfWatchers = it.numberOfWatchers,
      numberOfIssues = it.numberOfIssues,
      numberOfForks = it.numberOfForks
    )
  }
}

fun GithubRepositoryDetailsDto.mapToGithubRepositoryDetails(): GithubRepoDetails {
  return GithubRepoDetails(
    repositoryName = this.name,
    authorId = this.authorId,
    authorName = this.authorName,
    authorThumbnailImageUrl = this.authorThumbnailImageUrl,
    authorOnlineProfileUrl = this.authorOnlineProfileUrl,
    repositoryDescription = this.repositoryDescription,
    repositoryCreationDate = this.repositoryCreationDate.convertToUiDateFormat(),
    repositoryLastModificationDate = this.repositoryLastModificationDate.convertToUiDateFormat(),
    repositoryOnlineDetails = this.repositoryOnlineDetails,
    language = this.language,
    forks = this.forks,
    watchers = this.watchers,
    issues = this.issues,
    stars = this.stars
  )
}