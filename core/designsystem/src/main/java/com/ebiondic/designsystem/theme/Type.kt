package com.ebiondic.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.ebiondic.designsystem.R


val GexTypography = Typography(
  headlineLarge = TextStyle(
    fontFamily = FontFamily(Font(R.font.bold)),
    fontSize = 32.sp
  ),
  headlineMedium = TextStyle(
    fontFamily = FontFamily(Font(R.font.bold)),
    fontSize = 24.sp
  ),
  titleMedium = TextStyle(
    fontFamily = FontFamily(Font(R.font.bold)),
    fontSize = 12.sp
  ),
  titleSmall = TextStyle(
    fontFamily = FontFamily(Font(R.font.bold)),
    fontSize = 10.sp
  ),
  labelSmall = TextStyle(
    fontFamily = FontFamily(Font(R.font.medium)),
    fontSize = 10.sp
  ),
  labelMedium = TextStyle(
    fontFamily = FontFamily(Font(R.font.medium)),
    fontSize = 12.sp
  ),
  
  labelLarge = TextStyle(
    fontFamily = FontFamily(Font(R.font.bold)),
    fontSize = 14.sp
  ),
)