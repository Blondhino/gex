package com.ebiondic.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.details.action.DetailsScreenUiState

@Composable
internal fun DetailsRoute(
  modifier: Modifier = Modifier,
  viewModel: DetailsViewModel = hiltViewModel()
) {
  viewModel.getRepo()
  DetailsScreen(
    uiState = viewModel.uiState
  )
  
}

@Composable
internal fun DetailsScreen(
  modifier: Modifier = Modifier,
  uiState: DetailsScreenUiState
) {
  
  Box(modifier = Modifier.fillMaxSize()) {
    Text("Details screen")
  }
  
}