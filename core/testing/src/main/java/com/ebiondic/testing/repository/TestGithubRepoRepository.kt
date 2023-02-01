package com.ebiondic.testing.repository

import com.ebiondic.data.GithubRepoRepository
import com.ebiondic.model.dto.GithubRepoDto
import com.ebiondic.model.dto.GithubRepositoryDetailsDto
import com.ebiondic.model.exceptions.EndReached
import com.ebiondic.model.exceptions.NoResultsFound
import com.ebiondic.model.utils.unknownError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TestGithubRepoRepository : GithubRepoRepository {
  private var repositoryReturnsNoResults: Boolean = false
  private var repositoryReturnsUnknownError: Boolean = false
  private var repositoryReturnsEndReachedError: Boolean = false
  private val fakeSearchResultsResponse = listOf(
    GithubRepoDto(
      repositoryId = 1,
      repositoryName = "repo_name",
      authorName = "author_name",
      authorThumbnailImageUrl = "abc",
      numberOfIssues = 1,
      numberOfForks = 1,
      numberOfWatchers = 1,
    ),
    GithubRepoDto(
      repositoryId = 2,
      repositoryName = "repo_name2",
      authorName = "author_name2",
      authorThumbnailImageUrl = "abc2",
      numberOfIssues = 10,
      numberOfForks = 13,
      numberOfWatchers = 0,
    ),
  )
  private val fakeRepositoryDetailsResponse = GithubRepositoryDetailsDto(
    name = "repository_name",
    authorId = 1,
    authorName = "author_name",
    authorThumbnailImageUrl = "fake",
    authorOnlineProfileUrl = "fake",
    repositoryDescription = "fake",
    repositoryCreationDate = "fake",
    repositoryLastModificationDate = "fake",
    language = "fake",
    repositoryOnlineDetails = "fake",
    forks = 1,
    watchers = 1,
    issues = 1,
    stars = 1,
  )
  
  fun manageRepositorySearchSettings(
    shouldReturnNoResultsError: Boolean,
    shouldReturnUnknownError: Boolean,
    shouldReturnEndReachedError: Boolean,
  ) {
    repositoryReturnsNoResults = shouldReturnNoResultsError
    repositoryReturnsUnknownError = shouldReturnUnknownError
    repositoryReturnsEndReachedError = shouldReturnEndReachedError
  }
  
  fun manageRepositoryDetailsSettings(
    shouldReturnUnknownError: Boolean,
  ) {
    repositoryReturnsUnknownError = shouldReturnUnknownError
  }
  
  override suspend fun searchGithubRepository(
    repositoryName: String,
    sortCategory: String,
    sortDirection: String,
    page: Int
  ): Result<List<GithubRepoDto>> = withContext(Dispatchers.Unconfined) {
    if (repositoryReturnsNoResults) {
      return@withContext Result.failure(NoResultsFound())
    }
    if (repositoryReturnsUnknownError) {
      return@withContext unknownError()
    }
    if (repositoryReturnsEndReachedError) {
      return@withContext Result.failure(EndReached())
    }
    delay(200)
    return@withContext Result.success(fakeSearchResultsResponse)
  }
  
  override suspend fun getGithubRepositoryDetails(repositoryName: String, ownerName: String):
    Result<GithubRepositoryDetailsDto> = withContext(Dispatchers.Unconfined) {
    if (repositoryReturnsUnknownError) {
      return@withContext unknownError()
    } else {
      delay(100)
      return@withContext Result.success(fakeRepositoryDetailsResponse)
    }
  }
}