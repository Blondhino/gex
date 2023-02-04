package com.ebiondic.search

import com.ebiondic.common.SEARCH_DEBOUNCE_MILLIS
import com.ebiondic.designsystem.component.ASCENDING
import com.ebiondic.designsystem.component.DESCENDING
import com.ebiondic.designsystem.component.STARS
import com.ebiondic.designsystem.component.UPDATED
import com.ebiondic.domain.GetRepositorySearchResultsUseCase
import com.ebiondic.model.navigation.NavigateToDetailsArguments
import com.ebiondic.model.ui.GithubRepo
import com.ebiondic.search.action.SearchScreenEvent
import com.ebiondic.testing.repository.TestGithubRepoRepository
import com.ebiondic.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
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
  fun `search field is initially empty`() {
    val uiState = viewModel.uiState
    assertEquals(
      "",
      uiState.searchTerm
    )
  }
  
  @Test
  fun `sorting category is initially set to sort by Stars `() {
    val uiState = viewModel.uiState
    assertEquals(
      STARS,
      uiState.activeSortCategory
    )
  }
  
  @Test
  fun `sorting direction is initially set to Descending`() {
    val uiState = viewModel.uiState
    assertEquals(
      DESCENDING,
      uiState.sortDirection
    )
  }
  
  @Test
  fun `emitting SearchTermChanged updates uiState searchTerm`() {
    val testString = "test string"
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged(testString))
    
    assertEquals(
      testString,
      viewModel.uiState.searchTerm
    )
  }
  
  @Test
  fun `emitting SortCategoryClicked updates uiState's activeSortCategory`() {
    val testSearchCategory = UPDATED
    viewModel.onEvent(SearchScreenEvent.SortCategoryClicked(testSearchCategory))
    
    assertEquals(
      testSearchCategory,
      viewModel.uiState.activeSortCategory
    )
  }
  
  @Test
  fun `emitting OnSortDirectionClicked switches between sorting directions`() {
    var currentDirection = viewModel.uiState.sortDirection
    val expectedDirectionAfterEmitting = if (currentDirection == DESCENDING) ASCENDING else DESCENDING
    viewModel.onEvent(SearchScreenEvent.OnSortDirectionClicked)
    currentDirection = viewModel.uiState.sortDirection
    
    assertEquals(
      expectedDirectionAfterEmitting,
      currentDirection
    )
  }
  
  @Test
  fun `emitting OnRefresh updates uiState's isRefreshIndicatorVisible to true`() {
    viewModel.onEvent(SearchScreenEvent.OnRefresh)
    val isRefreshingIndicatorVisible = viewModel.uiState.isRefreshingIndicatorVisible
    
    assertEquals(
      true,
      isRefreshingIndicatorVisible
    )
  }
  
  @Test
  fun `view model provides valid navigation arguments based on repository id`() {
    val expectedRepositoryName = "repository_name"
    val expectedOwnerName = "owner_name"
    val repositoryId = 1
    val repositories = listOf(
      GithubRepo(
        repositoryId = repositoryId,
        repositoryName = expectedRepositoryName,
        authorName = expectedOwnerName,
        authorThumbnailImageUrl = "",
        numberOfIssues = 1,
        numberOfForks = 1,
        numberOfWatchers = 1,
      ),
    )
    viewModel.uiState = viewModel.uiState.copy(repositories = repositories)
    val expectedNavArguments = NavigateToDetailsArguments(
      repositoryName = expectedRepositoryName,
      ownerName = expectedOwnerName
    )
    val actualNavArguments = viewModel.getNavArgumentsForDetails(repositoryId)
    
    assertEquals(
      expectedNavArguments,
      actualNavArguments
    )
  }
  
  @Test
  fun `view model provides default navigation arguments when wrong repository id is given`() {
    val expectedRepositoryName = ""
    val expectedOwnerName = ""
    val repositoryId = 1
    val repositories = listOf<GithubRepo>()
    viewModel.uiState = viewModel.uiState.copy(repositories = repositories)
    val expectedNavArguments = NavigateToDetailsArguments(
      repositoryName = expectedRepositoryName,
      ownerName = expectedOwnerName
    )
    val actualNavArguments = viewModel.getNavArgumentsForDetails(repositoryId)
    
    assertEquals(
      expectedNavArguments,
      actualNavArguments
    )
  }
  
  @Test
  fun `when there is no search results uiState's noResultsFound is set to true `() = runTest {
    githubRepoRepository.manageRepositorySearchSettings(
      shouldReturnNoResultsError = true,
      shouldReturnUnknownError = false,
      shouldReturnEndReachedError = false
    )
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged("Fake entry"))
    delay(1000)
    assertEquals(
      true,
      viewModel.uiState.noResultsFound
    )
  }
  
  @Test
  fun `when search term is empty, uiState's isSearchEmpty is set to true`() {
    githubRepoRepository.manageRepositorySearchSettings(
      shouldReturnNoResultsError = false,
      shouldReturnUnknownError = false,
      shouldReturnEndReachedError = false
    )
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged(""))
    assertEquals(
      true,
      viewModel.uiState.isSearchEmpty
    )
  }
  
  @Test
  fun `when search term is empty, uiState's repositories are set to empty list`() {
    githubRepoRepository.manageRepositorySearchSettings(
      shouldReturnNoResultsError = false,
      shouldReturnUnknownError = false,
      shouldReturnEndReachedError = false
    )
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged(""))
    assertEquals(
      0,
      viewModel.uiState.repositories.size
    )
  }
  
  @Test
  fun `when search term is empty api is not triggered`() = runTest {
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged(""))
    delay(SEARCH_DEBOUNCE_MILLIS)
    assertEquals(
      false,
      viewModel.uiState.isLoading
    )
  }
  
  @Test
  fun `when search response returns endReached uiState's isEndReached is set to true`() = runTest {
    githubRepoRepository.manageRepositorySearchSettings(
      shouldReturnNoResultsError = false,
      shouldReturnUnknownError = false,
      shouldReturnEndReachedError = true
    )
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged("fake term"))
    delay(1000)
    assertEquals(
      true,
      viewModel.uiState.isEndReached
    )
  }
  
  @Test
  fun `typing in search faster than debounce will not execute search`() = runTest {
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged("fake term"))
    assertEquals(
      false,
      viewModel.uiState.isLoading
    )
  }
  
  @Test
  fun `search is triggered after debounce time is up`() = runTest {
    viewModel.onEvent(SearchScreenEvent.SearchTermChanged("fake term"))
    delay(SEARCH_DEBOUNCE_MILLIS)
    assertEquals(
      true,
      viewModel.uiState.isLoading
    )
  }
  
}