package com.ebiondic.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.ebiondic.designsystem.theme.smallPadding


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
    onClick = { onClick() }
  ) {
    Text(modifier = Modifier.padding(smallPadding), text = text)
  }
}