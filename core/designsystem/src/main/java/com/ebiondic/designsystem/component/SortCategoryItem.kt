package com.ebiondic.designsystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ebiondic.designsystem.theme.smallPadding

@Composable
fun SortCategoryItem(
  modifier: Modifier,
  onSelected: () -> Unit = {},
  isActive: Boolean = false,
  text: String = ""
) {
  val textColor by animateColorAsState(targetValue = if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary)
  val backgroundColor by animateColorAsState(
    targetValue = if (isActive) MaterialTheme.colorScheme.secondary else MaterialTheme
      .colorScheme.surface
  )
  Box(
    modifier
      .clickable { onSelected() }
      .background(backgroundColor)
      .padding(smallPadding),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = text,
      style = MaterialTheme.typography.labelMedium.copy(color = textColor)
    )
  }
}

enum class SortCategory {
  STARS,
  FORKS,
  UPDATED,
  NONE,
}