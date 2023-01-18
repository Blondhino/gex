package com.ebiondic.convention.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *>) {
  val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
 
  commonExtension.apply {
    
    configureKotlinAndroid(this)
    
    buildFeatures {
      compose = true
    }
    composeOptions {
      kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
    }
    
  }
}