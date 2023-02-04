package com.ebiondic.details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ebiondic.designsystem.component.GexButton
import com.ebiondic.designsystem.component.LoadingIndicator
import com.ebiondic.designsystem.component.RepositoryDetailsHeader
import com.ebiondic.designsystem.component.ShortRepositoryOverview
import com.ebiondic.designsystem.theme.largePadding
import com.ebiondic.designsystem.theme.smallPadding
import com.ebiondic.details.action.DetailsScreenUiState

@Composable
internal fun DetailsRoute(
  modifier: Modifier = Modifier,
  viewModel: DetailsViewModel = hiltViewModel(),
  onScreenError: () -> Unit,
) {
  val context = LocalContext.current
  val screenError = viewModel.uiState.screenError
  LaunchedEffect(key1 = context) {
    viewModel.getRepo()
  }
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
  LaunchedEffect(key1 = screenError) {
    if (screenError.isNotEmpty()) {
      Toast.makeText(context, screenError, Toast.LENGTH_SHORT).show()
      onScreenError()
    }
  }
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
      
      Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(smallPadding)) {
        RepositoryDetailsHeader(
          repositoryName = uiState.details.repositoryName,
          authorProfileImageUrl = uiState.details.authorThumbnailImageUrl,
          authorName = uiState.details.authorName,
          onAuthorNameClicked = { onAuthorNameCLicked() }
        )
        
        ShortRepositoryOverview(
          modifier = Modifier
            .fillMaxWidth()
            .padding(smallPadding),
          forks = uiState.details.forks,
          watchers = uiState.details.watchers,
          stars = uiState.details.stars,
          issues = uiState.details.issues
        )
        
        ShortInfoTextItem(
          modifier = Modifier.padding(horizontal = smallPadding),
          type = stringResource(R.string.repository_programing_language_label),
          value = uiState.details.language
        )
        ShortInfoTextItem(
          modifier = Modifier.padding(horizontal = smallPadding),
          type = stringResource(R.string.repository_creation_label),
          value = uiState.details.repositoryCreationDate
        )
        ShortInfoTextItem(
          modifier = Modifier.padding(horizontal = smallPadding),
          type = stringResource(R.string.repository_modification_label),
          value = uiState.details.repositoryLastModificationDate
        )
        
        Spacer(modifier = Modifier.height(largePadding))
        
        Text(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = smallPadding),
          text = uiState.details.repositoryDescription,
          style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
        )
      }
      
      
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
  
  LoadingIndicator(isLoading = uiState.isLoading)
  
}

@Composable
fun ShortInfoTextItem(
  modifier: Modifier = Modifier,
  type: String,
  value: String
) {
  Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(smallPadding)) {
    Text(
      type,
      style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.surface)
    )
    Text(
      value,
      style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
    )
  }
}