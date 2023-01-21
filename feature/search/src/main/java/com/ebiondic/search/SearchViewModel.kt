package com.ebiondic.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ebiondic.search.action.SearchScreenEvent
import com.ebiondic.search.action.SearchScreenEvent.SearchTermChanged
import com.ebiondic.search.action.SearchScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
  var uiState by mutableStateOf(SearchScreenUiState())
  
  fun onEvent(event: SearchScreenEvent) {
    when (event) {
      is SearchTermChanged -> {
        uiState = uiState.copy(searchTerm = event.term)
      }
    }
  }
}