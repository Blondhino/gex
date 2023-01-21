package com.ebiondic.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OnlineImage(
  modifier: Modifier = Modifier,
  fullSizeImageUrl: String,
  thumbnailImageUrl: String = "",
  contentScale: ContentScale = ContentScale.Crop,
) {
  val requestManager: RequestManager = LocalContext.current.let { remember(it) { Glide.with(it) } }
  GlideImage(
    modifier = modifier,
    model = fullSizeImageUrl,
    contentDescription = null,
    contentScale = contentScale
  ) {
    it.thumbnail(
      requestManager
        .asDrawable()
        .load(thumbnailImageUrl)
    )
  }
}