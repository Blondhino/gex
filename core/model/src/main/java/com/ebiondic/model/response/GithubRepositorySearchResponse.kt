package com.ebiondic.model.response

data class GithubRepositorySearchResponse(
  val id: Int?,
  val name: String?,
  val owner: Owner?,
  val watchers_count: Int?,
  val forks_count: Int?,
  val open_issues_count: Int?,
  
  )

data class Owner(
  val id: Int?,
  val login: String?,
  val avatar_url: String?,
  val url: String?
)
