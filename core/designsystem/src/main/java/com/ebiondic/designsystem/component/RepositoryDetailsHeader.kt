package com.ebiondic.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.ebiondic.designsystem.theme.smallPadding

@Composable
fun RepositoryDetailsHeader(
  modifier: Modifier = Modifier,
  repositoryName: String,
  authorProfileImageUrl: String,
  authorOnlineProfileUrl: String,
  authorName: String,
) {
  
  Box(modifier = modifier.fillMaxWidth()) {
    OnlineImage(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.40f),
      contentScale = ContentScale.Crop,
      fullSizeImageUrl = authorProfileImageUrl
    )
    Box(
      modifier = Modifier
        .matchParentSize()
        .background(
          Brush.verticalGradient(
            colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background)
          )
        )
    )
    
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomCenter)
        .padding(smallPadding),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = repositoryName,
        style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
      )
      
      Row(horizontalArrangement = Arrangement.spacedBy(smallPadding)) {
        Text(
          text = "by:",
          style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
        )
        Text(
          text = authorName,
          style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.secondary)
        )
      }
    }
  }
  
}