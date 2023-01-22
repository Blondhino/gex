package com.ebiondic.network

import com.ebiondic.model.response.GithubRepositorySearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
  
  @GET("search/repositories")
  suspend fun searchRepositories(
    @Query("q") repositoryName: String,
    @Query("sort") sortCategory: String,
    @Query("order") sortDirection: String
  ): GithubRepositorySearchResponse
}