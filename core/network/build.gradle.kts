plugins {
  id("gex.android.library")
  id("gex.android.hilt")
}

android {
  namespace = "com.ebiondic.network"
}

dependencies {
  implementation(project(":core:model"))
  implementation(libs.okhttp.logging)
  implementation(libs.retrofit.core)
  implementation(libs.retrofit.gson.converter)
}