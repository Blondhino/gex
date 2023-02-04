package com.ebiondic.model.dto

data class GithubRepoDto(
  val repositoryId: Int,
  val repositoryName: String,
  val authorName: String,
  val authorThumbnailImageUrl: String,
  val authorOnlineProfileUrl: String,
  val numberOfWatchers: Int,
  val numberOfForks: Int,
  val numberOfIssues: Int
)
