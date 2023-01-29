package com.ebiondic.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ebiondic.designsystem.theme.smallPadding

@Composable
fun Hint(
  modifier: Modifier = Modifier,
  @DrawableRes image: Int,
  @StringRes message: Int
) {
  
  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(smallPadding),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Icon(
      modifier = Modifier.size(70.dp),
      painter = painterResource(id = image),
      contentDescription = null,
      tint = MaterialTheme.colorScheme.surface
    )
    Text(
      text = stringResource(id = message),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.surface)
    )
  }
  
}
