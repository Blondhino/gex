package com.ebiondic.designsystem.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.*
import com.ebiondic.designsystem.R

@Composable
fun LoadingIndicator(isLoading: Boolean) {
  if (isLoading) {
    Dialog(onDismissRequest = {}) {
      val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_animation))
      val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLoading,
        restartOnPlay = false
      )
      LottieAnimation(
        modifier = Modifier.fillMaxSize(),
        composition = composition,
        progress = progress
      )
    }
  }
}