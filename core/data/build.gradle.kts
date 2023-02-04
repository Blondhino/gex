plugins {
 id("gex.android.library")
 id("gex.android.hilt")
}

android {
  namespace = "com.ebiondic.data"
}

dependencies {
  implementation(project(":core:network"))
  implementation(project(":core:model"))
  implementation(libs.kotlinx.coroutines.android)
}