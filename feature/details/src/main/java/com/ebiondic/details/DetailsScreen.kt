package com.ebiondic.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.designsystem.component.RepositoryDetailsHeader
import com.ebiondic.designsystem.theme.smallPadding
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
  
  uiState.details?.let {
    Box(modifier = Modifier.fillMaxSize()) {
      
      RepositoryDetailsHeader(
        repositoryName = uiState.details.repositoryName,
        authorProfileImageUrl = uiState.details.authorThumbnailImageUrl,
        authorOnlineProfileUrl = uiState.details.authorOnlineProfileUrl,
        authorName = uiState.details.authorName
      )
      
      GexButton(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.BottomCenter),
        buttonColor = MaterialTheme.colorScheme.secondary,
        text = "Visit project online",
        onClick = {}
      )
    }
  }
  
}

@Composable
fun GexButton(
  modifier: Modifier = Modifier,
  text: String = "",
  buttonColor: Color = MaterialTheme.colorScheme.secondary,
  onClick: () -> Unit = {}
) {
  Button(
    modifier = modifier,
    shape = RectangleShape,
    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
    onClick = { }
  ) {
    Text(modifier = Modifier.padding(smallPadding), text = text)
  }
}