plugins {
  id("gex.android.library")
  id("gex.android.hilt")
}

android {
  namespace = "com.ebiondic.common"
}

dependencies {
  implementation(libs.kotlinx.coroutines.android)
  testImplementation(project(":core:testing"))
}