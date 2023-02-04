package com.ebiondic.model.ui

data class GithubRepoDetails(
  val repositoryName: String,
  val authorId: Int,
  val authorName: String,
  val authorThumbnailImageUrl: String,
  val authorOnlineProfileUrl: String,
  val repositoryDescription: String,
  val repositoryCreationDate: String,
  val repositoryLastModificationDate: String,
  val language: String,
  val repositoryOnlineDetails: String,
  val forks : Int,
  val watchers : Int,
  val issues : Int,
  val stars : Int,
)