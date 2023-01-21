package com.ebiondic.model.dto

data class GithubRepoDto(
  val repositoryName: String,
  val authorName: String,
  val authorThumbnailImageUrl: String,
  val numberOfWatchers: Int,
  val numberOfForks: Int,
  val numberOfIssues: Int
)
