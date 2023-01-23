package com.ebiondic.model.ui

data class GithubRepo(
  val repositoryId : Int,
  val repositoryName: String,
  val authorName: String,
  val authorThumbnailImageUrl: String,
  val numberOfWatchers: Int,
  val numberOfForks: Int,
  val numberOfIssues: Int
)