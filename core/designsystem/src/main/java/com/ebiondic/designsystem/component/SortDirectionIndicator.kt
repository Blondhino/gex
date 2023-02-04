package com.ebiondic.designsystem.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

const val ASCENDING = 0
const val DESCENDING = 1

@Composable
fun SortDirectionIndicator(
  onSortDirectionClick: () -> Unit = {},
  sortDirection: Int = ASCENDING
) {
  val rotation by animateFloatAsState(targetValue = if (sortDirection == DESCENDING) 0f else 180f)
  Box(
    modifier = Modifier
      .clip(CircleShape)
      .size(30.dp)
      .background(color = MaterialTheme.colorScheme.secondary)
      .rotate(rotation)
      .clickable { onSortDirectionClick() },
    contentAlignment = Alignment.Center
  ) {
    Icon(Icons.Filled.ArrowCircleDown, contentDescription = null)
  }
}

