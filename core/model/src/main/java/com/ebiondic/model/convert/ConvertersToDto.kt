package com.ebiondic.model.convert

import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.ui.GithubRepo

fun List<GithubRepoDto>.mapToListOfGithubRepositories(): List<GithubRepo> {
  return this.map {
    GithubRepo(
      repositoryId = it.repositoryId,
      repositoryName = it.repositoryName,
      authorName = it.authorName,
      authorThumbnailImageUrl = it.authorThumbnailImageUrl,
      numberOfWatchers = it.numberOfWatchers,
      numberOfIssues = it.numberOfIssues,
      numberOfForks = it.numberOfForks
    )
  }
}