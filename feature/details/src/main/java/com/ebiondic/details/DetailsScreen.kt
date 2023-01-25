package com.ebiondic.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.designsystem.component.GexButton
import com.ebiondic.designsystem.component.RepositoryDetailsHeader
import com.ebiondic.details.action.DetailsScreenUiState

@Composable
internal fun DetailsRoute(
  modifier: Modifier = Modifier,
  viewModel: DetailsViewModel = hiltViewModel()
) {
  val context = LocalContext.current
  
  viewModel.getRepo()
  DetailsScreen(
    modifier = modifier,
    uiState = viewModel.uiState,
    onAuthorNameCLicked = {
      ContextCompat.startActivity(
        context, viewModel.getIntentForOpeningAuthorOnlineProfile(), null
      )
    },
    onVisitProjectOnlineClicked = {
      ContextCompat.startActivity(
        context, viewModel.getIntentForOpeningProjectOnlinePage(), null
      )
    }
  )
  
}

@Composable
internal fun DetailsScreen(
  modifier: Modifier = Modifier,
  uiState: DetailsScreenUiState,
  onAuthorNameCLicked: () -> Unit = {},
  onVisitProjectOnlineClicked: () -> Unit = {}
) {
  
  uiState.details?.let {
    Box(modifier = modifier.fillMaxSize()) {
      
      RepositoryDetailsHeader(
        repositoryName = uiState.details.repositoryName,
        authorProfileImageUrl = uiState.details.authorThumbnailImageUrl,
        authorName = uiState.details.authorName,
        onAuthorNameClicked = { onAuthorNameCLicked() }
      )
      
      GexButton(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.BottomCenter),
        buttonColor = MaterialTheme.colorScheme.secondary,
        text = stringResource(R.string.visit_project_online_button_title),
        onClick = { onVisitProjectOnlineClicked() }
      )
    }
  }
  
}
