package com.ebiondic.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebiondic.common.WebUrlLauncher
import com.ebiondic.common.decoder.StringDecoder
import com.ebiondic.details.action.DetailsScreenEvent
import com.ebiondic.details.action.DetailsScreenEvent.OnOpenOnlineRepositoryDetailsClicked
import com.ebiondic.details.action.DetailsScreenEvent.OnOpenOnlineUserDetailsClicked
import com.ebiondic.details.action.DetailsScreenUiState
import com.ebiondic.details.navigation.DetailsArgs
import com.ebiondic.domain.GetRepositoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  stringDecoder: StringDecoder,
  val getRepositoryDetailsUseCase: GetRepositoryDetailsUseCase,
  val launchWebUrl: WebUrlLauncher
) : ViewModel() {
  var uiState by mutableStateOf(DetailsScreenUiState())
  private val detailsArgs: DetailsArgs = DetailsArgs(savedStateHandle, stringDecoder)
  
  fun onEvent(event: DetailsScreenEvent) {
    when (event) {
      is OnOpenOnlineRepositoryDetailsClicked -> {
        launchWebUrl(uiState.details?.repositoryOnlineDetails.orEmpty())
      }
      is OnOpenOnlineUserDetailsClicked -> {
        launchWebUrl(uiState.details?.authorOnlineProfileUrl.orEmpty())
      }
    }
  }
  
  fun getRepo() = viewModelScope.launch {
    uiState = uiState.copy(isLoading = true)
    getRepositoryDetailsUseCase(repositoryName = detailsArgs.repositoryName, ownerName = detailsArgs.ownerName)
      .onSuccess {
        uiState = uiState.copy(details = it)
      }
      .onFailure {
        showError(it.message.orEmpty())
      }
    uiState = uiState.copy(isLoading = false)
  }
  
  private fun showError(error: String) = viewModelScope.launch {
    uiState = uiState.copy(screenError = error)
    delay(100)
    uiState = uiState.copy(screenError = "")
  }
  
}