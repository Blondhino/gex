package com.ebiondic.model.response

import androidx.annotation.Keep

@Keep
data class GithubRepositorySearchResponse(
  val items: List<Repository>
)

@Keep
data class Repository(
  val id: Int?,
  val name: String?,
  val owner: Owner?,
  val watchers_count: Int?,
  val forks_count: Int?,
  val open_issues_count: Int?,
)

@Keep
data class Owner(
  val id: Int?,
  val login: String?,
  val avatar_url: String?,
  val html_url: String?
)
