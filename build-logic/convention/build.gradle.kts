plugins {
  `kotlin-dsl`
}
java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.ebiondic.gex.buildlogic"

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
  
    register("androidLibraryCompose"){
      id = "gex.android.library.compose"
      implementationClass = "AndroidComposeLibraryConventionPlugin"
    }
  
    register("androidHilt") {
      id = "gex.android.hilt"
      implementationClass = "AndroidHiltConventionPlugin"
    }
    
  }
}