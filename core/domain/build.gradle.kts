plugins {
  id("gex.android.library")
  kotlin("kapt")
}

android {
  namespace = "com.ebiondic.domain"
}

dependencies {
  implementation(project(":core:data"))
  implementation(project(":core:model"))
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}