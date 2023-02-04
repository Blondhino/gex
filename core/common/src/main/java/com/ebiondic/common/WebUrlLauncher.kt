package com.ebiondic.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WebUrlLauncher @Inject constructor(@ApplicationContext val context: Context) {
  operator fun invoke(url: String) {
    if (url.isNotEmpty()) {
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      ContextCompat.startActivity(
        context, intent, null
      )
    }
  }
}