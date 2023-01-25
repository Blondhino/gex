package com.ebiondic.details

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebiondic.common.decoder.StringDecoder
import com.ebiondic.details.action.DetailsScreenUiState
import com.ebiondic.details.navigation.DetailsArgs
import com.ebiondic.domain.GetRepositoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  stringDecoder: StringDecoder,
  val getRepositoryDetailsUseCase: GetRepositoryDetailsUseCase
) : ViewModel() {
  var uiState by mutableStateOf(DetailsScreenUiState())
  private val detailsArgs: DetailsArgs = DetailsArgs(savedStateHandle, stringDecoder)
  
  fun getRepo() = viewModelScope.launch {
    getRepositoryDetailsUseCase(repositoryName = detailsArgs.repositoryName, ownerName = detailsArgs.ownerName)
      .onSuccess {
        uiState = uiState.copy(details = it)
      }
      .onFailure { }
  }
  
  fun getIntentForOpeningAuthorOnlineProfile(): Intent {
    return Intent(Intent.ACTION_VIEW, Uri.parse(uiState.details?.authorOnlineProfileUrl))
  }
  
  fun getIntentForOpeningProjectOnlinePage(): Intent {
    return Intent(Intent.ACTION_VIEW, Uri.parse(uiState.details?.repositoryOnlineDetails))
  }
  
  
}