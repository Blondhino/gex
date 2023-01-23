package com.ebiondic.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ebiondic.common.decoder.StringDecoder
import com.ebiondic.details.action.DetailsScreenUiState
import com.ebiondic.details.navigation.DetailsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  stringDecoder: StringDecoder,
) : ViewModel() {
  var uiState by mutableStateOf(DetailsScreenUiState())
  private val detailsArgs: DetailsArgs = DetailsArgs(savedStateHandle, stringDecoder)
  
  fun getRepo() {
    Log.d("repositoryDetails", "${detailsArgs.repositoryName} ${detailsArgs.ownerName}" )
  }
  
}