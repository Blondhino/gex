plugins {
  id("gex.android.library")
  id("gex.android.library.compose")
  id("gex.android.hilt")
}

android {
  namespace = "com.ebiondic.testing"
}

dependencies {
  implementation(project(":core:common"))
  implementation(project(":core:data"))
  implementation(project(":core:model"))
  androidTestImplementation(project(":core:testing"))
  
  api(libs.junit4)
  api(libs.androidx.test.core)
  api(libs.kotlinx.coroutines.test)
  api(libs.turbine)
  
  api(libs.androidx.test.espresso.core)
  api(libs.androidx.test.runner)
  api(libs.androidx.test.rules)
  api(libs.androidx.compose.ui.test)
  api(libs.hilt.android.testing)
  
  debugApi(libs.androidx.compose.ui.testManifest)
}