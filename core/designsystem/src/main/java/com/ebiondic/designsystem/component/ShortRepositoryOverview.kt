package com.ebiondic.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ebiondic.designsystem.R

@Composable
fun ShortRepositoryOverview(
  modifier: Modifier = Modifier,
  forks: Int,
  watchers: Int,
  stars: Int,
  issues: Int
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    ShortInfoIconItem(icon = R.drawable.ic_fork, text = forks.toString())
    ShortInfoIconItem(icon = R.drawable.ic_watching, text = watchers.toString())
    ShortInfoIconItem(icon = R.drawable.ic_star, text = stars.toString())
    ShortInfoIconItem(icon = R.drawable.ic_circle, text = issues.toString())
  }
}