package com.ebiondic.domain

import com.ebiondic.data.GithubRepoRepository
import com.ebiondic.model.convert.mapToListOfGithubRepositories
import com.ebiondic.model.enum.SortCategory
import com.ebiondic.model.enum.SortDirection
import com.ebiondic.model.exceptions.EndReached
import com.ebiondic.model.exceptions.NoResultsFound
import com.ebiondic.model.ui.GithubRepo
import com.ebiondic.model.utils.unknownError
import javax.inject.Inject

class GetRepositorySearchResultsUseCase @Inject constructor(
  private val githubRepoRepository: GithubRepoRepository
) {
  private var lastFetchedPage = 0
  private val repositories = arrayListOf<GithubRepo>()
  
  suspend operator fun invoke(
    repositoryName: String,
    sortCategory: SortCategory = SortCategory.NONE,
    sortDirection: SortDirection = SortDirection.DESCENDING,
    isNewSearch: Boolean = true,
  ): Result<List<GithubRepo>> {
    
    githubRepoRepository.searchGithubRepository(
      repositoryName = repositoryName,
      sortCategory = sortCategory.apiName,
      sortDirection = sortDirection.apiName,
      page = if (isNewSearch) 1 else lastFetchedPage + 1
    )
      .onSuccess {
        lastFetchedPage += 1
        if (isNewSearch) {
          repositories.clear()
          repositories.addAll(it.mapToListOfGithubRepositories())
        } else {
          repositories.addAll(it.mapToListOfGithubRepositories())
        }
        if (it.isEmpty()) {
          if (isNewSearch)
            return Result.failure(NoResultsFound())
          return Result.failure(EndReached())
        }
        
        return Result.success(repositories)
      }
      .onFailure {
        return Result.failure(it)
      }
    return unknownError()
  }
}