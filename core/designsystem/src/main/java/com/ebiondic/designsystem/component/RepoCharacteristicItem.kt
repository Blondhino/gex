package com.ebiondic.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebiondic.designsystem.R
import com.ebiondic.designsystem.theme.smallPadding

@Composable
fun ShortInfoIconItem(
  modifier: Modifier = Modifier,
  @DrawableRes icon: Int = 0,
  text: String = ""
) {
  
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(smallPadding)
  ) {
    Icon(
      modifier = Modifier.size(20.dp),
      painter = painterResource(id = icon),
      contentDescription = null,
      tint = MaterialTheme.colorScheme.surface,
    )
    Text(text, style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.surface))
  }
  
}

@Preview
@Composable
fun previewShortInfoItem() {
  ShortInfoIconItem(icon = R.drawable.ic_fork, text = "220")
}