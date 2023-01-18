plugins {
  id("com.android.library")
  id("gex.android.library.compose")
}

android {
  namespace = "com.ebiondic.designsystem"
}

dependencies {
  implementation(libs.androidx.core.ktx)
  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
  api(libs.androidx.compose.material3)
  api(libs.androidx.material)
  debugApi(libs.androidx.compose.ui.tooling)
  api(libs.androidx.compose.ui.tooling.preview)
  api(libs.androidx.compose.ui.util)
  api(libs.androidx.compose.runtime)
}