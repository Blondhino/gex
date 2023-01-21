import com.android.build.gradle.LibraryExtension
import com.ebiondic.convention.extensions.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidComposeLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
      }
      
      extensions.configure<LibraryExtension> {
        configureAndroidCompose(this)
        defaultConfig.targetSdk = 33
      }
      
    }
  }
}