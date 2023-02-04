package com.ebiondic.details

import androidx.lifecycle.SavedStateHandle
import com.ebiondic.details.navigation.ownerNameArg
import com.ebiondic.details.navigation.repositoryNameArg
import com.ebiondic.domain.GetRepositoryDetailsUseCase
import com.ebiondic.testing.decoder.FakeStringDecoder
import com.ebiondic.testing.repository.TestGithubRepoRepository
import com.ebiondic.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {
  
  @get:Rule
  val dispatcherRule = MainDispatcherRule()
  private lateinit var viewModel: DetailsViewModel
  private val fakeSavedStateHandle = SavedStateHandle(
    mapOf(
      repositoryNameArg to "fake_repository_name",
      ownerNameArg to "fake_owner_name"
    )
  )
  private val fakeStringDecoder = FakeStringDecoder()
  private val fakeGithubRepoRepository = TestGithubRepoRepository()
  private val fakeGetRepositoryDetailsUseCase = GetRepositoryDetailsUseCase(
    githubRepoRepository = fakeGithubRepoRepository
  )
  
  @Before
  fun setup() {
    viewModel = DetailsViewModel(
      savedStateHandle = fakeSavedStateHandle,
      stringDecoder = fakeStringDecoder,
      getRepositoryDetailsUseCase = fakeGetRepositoryDetailsUseCase
    )
  }
  
  @Test
  fun `uiState is initially loading`() {
    val uiState = viewModel.uiState
    assertTrue(uiState.isLoading)
  }
  
  @Test
  fun `when fetching fails uiState's screenError holds error message`() {
    fakeGithubRepoRepository.manageRepositoryDetailsSettings(shouldReturnUnknownError = true)
    viewModel.getRepo()
    assertTrue(viewModel.uiState.screenError.isNotEmpty())
  }
  
  
  @Test
  fun `while fetching repository details, uiState's isLoading is set to true`() = runTest {
    fakeGithubRepoRepository.manageRepositoryDetailsSettings(shouldReturnUnknownError = false)
    viewModel.getRepo()
    assertTrue(viewModel.uiState.isLoading)
  }
  
  
}