plugins {
  id("com.android.library")
  id("gex.android.library.compose")
}

android {
  namespace = "com.ebiondic.designsystem"
  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.glide.compose)
  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
  api(libs.androidx.compose.material3)
  api(libs.androidx.material)
  debugApi(libs.androidx.compose.ui.tooling)
  api(libs.androidx.compose.ui.tooling.preview)
  api(libs.androidx.compose.ui.util)
  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.ui.constraint)
  api(libs.androidx.compose.material.iconsExtended)
}