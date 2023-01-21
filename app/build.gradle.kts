plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("gex.android.application.compose")
  id("gex.android.hilt")
}
android {
  defaultConfig {
    applicationId = "com.ebiondic.gex"
    versionCode = 1
    versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    
    vectorDrawables {
      useSupportLibrary = true
    }
  }
  
  buildTypes {
    val debug by getting {
      applicationIdSuffix = ".debug"
    }
    val release by getting {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      
      // To publish on the Play store a private signing key is required, but to allow anyone
      // who clones the code to sign and run the release variant, use the debug signing key.
      // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
      signingConfig = signingConfigs.getByName("debug")
    }
  }
  
  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
  namespace = "com.ebiondic.gex"
}

dependencies {
  implementation(project(":core:designsystem"))
  implementation(project(":feature:search"))
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.lifecycle.runtimeCompose)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.hilt.navigation.compose)
  
}