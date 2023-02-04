package com.ebiondic.model.response

import androidx.annotation.Keep

@Keep
data class GithubRepositoryDetailsResponse(
  val name: String?,
  val owner: Owner?,
  val description: String?,
  val created_at: String?,
  val updated_at: String?,
  val language: String?,
  val html_url: String?,
  val forks_count: Int?,
  val watchers_count: Int?,
  val open_issues_count: Int?,
  val stargazers_count: Int?,
)

