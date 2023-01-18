plugins {
  `kotlin-dsl`
}
java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.ebiondic.ventium.buildlogic"

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
  plugins {
    
    register("androidApplicationCompose"){
      id = "gex.android.application.compose"
      implementationClass = "AndroidComposeApplicationConventionPlugin"
    }
    
  }
}