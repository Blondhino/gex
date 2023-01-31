package com.ebiondic.search

import com.ebiondic.domain.GetRepositorySearchResultsUseCase
import com.ebiondic.testing.repository.TestGithubRepoRepository
import com.ebiondic.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {
  
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()
  
  private lateinit var viewModel: SearchViewModel
  private val githubRepoRepository = TestGithubRepoRepository()
  private val getRepositorySearchResultUseCase = GetRepositorySearchResultsUseCase(
    githubRepoRepository = githubRepoRepository
  )
  
  @Before
  fun setup() {
    viewModel = SearchViewModel(
      getRepositorySearchResults = getRepositorySearchResultUseCase
    )
  }
  
  @Test
  fun `example test`() = runTest {
    assertEquals(
      true,
      true
    )
  }
  
}