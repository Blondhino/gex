package com.ebiondic.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val DarkColorScheme = darkColorScheme(
  primary = BlackNight,
  onPrimary = LavenderBlush,
  secondary = Razzmatazz,
  onSecondary = ShadyLady,
  surface = Scorpion,
  background = BlackNight
)

val LightColorScheme = lightColorScheme(
  primary = LavenderBlush,
  onPrimary = BlackNight,
  secondary = Razzmatazz,
  onSecondary = ShadyLady,
  surface = Scorpion,
  background = LavenderBlush
)

@Composable
fun GexTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme,
    typography = GexTypography,
    content = content
  )
  
}
