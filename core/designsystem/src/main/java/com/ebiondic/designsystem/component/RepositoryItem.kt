package com.ebiondic.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ebiondic.designsystem.R
import com.ebiondic.designsystem.theme.largePadding
import com.ebiondic.designsystem.theme.smallPadding

@Composable
fun RepositoryItem(
  modifier: Modifier = Modifier,
  repositoryName: String,
  authorName: String,
  authorThumbnailImageUrl: String,
  numberOfWatchers: Int,
  numberOfForks: Int,
  numberOfIssues: Int,
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(largePadding)
  ) {
    OnlineImage(
      modifier = Modifier
        .size(50.dp)
        .clip(CircleShape),
      fullSizeImageUrl = authorThumbnailImageUrl,
    )
    Column(modifier = modifier.fillMaxWidth()) {
      Text(repositoryName, style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.secondary))
      Text(authorName, style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.surface))
      Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(smallPadding)
      ) {
        RepositoryCharacteristic(iconId = R.drawable.ic_fork, value = numberOfForks)
        RepositoryCharacteristic(iconId = R.drawable.ic_watching, value = numberOfWatchers)
        RepositoryCharacteristic(iconId = R.drawable.ic_circle, value = numberOfIssues)
      }
    }
  }
}

@Composable
fun RepositoryCharacteristic(
  @DrawableRes iconId: Int,
  value: Int,
) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Icon(
      modifier = Modifier.size(12.dp),
      painter = painterResource(id = iconId),
      contentDescription = null,
      tint = MaterialTheme.colorScheme.surface
    )
    Text(value.toString(), style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.surface))
  }
}