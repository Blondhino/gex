import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("gex.android.library")
        apply("gex.android.hilt")
      }
      
      extensions.configure<LibraryExtension> {}
      
      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
      
      dependencies {
        add("implementation", project(":core:designsystem"))
        add("implementation", project(":core:domain"))
        add("implementation", project(":core:common"))
        
        add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
        add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
      }
    }
  }
}