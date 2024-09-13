import com.seiko.flutter.kmp.androidLibrary
import com.seiko.flutter.kmp.cocoapods
import com.seiko.flutter.kmp.kotlin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.all

class FlutterLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.kotlin.native.cocoapods")
            apply("com.android.library")
        }
        kotlin {
            androidTarget()
            iosX64()
            iosArm64()
            iosSimulatorArm64()
            sourceSets.apply {
                all {
                    languageSettings {
                        enableLanguageFeature("ExpectActualClasses")
                    }
                }
                androidMain {
                    dependencies {
                        val flutterEngineVersion = "fb483d79c8b3d89555d8c876058d6d4b862d31fd"
                        compileOnly("io.flutter:flutter_embedding_debug:1.0.0-$flutterEngineVersion")
                    }
                }
                iosMain {
                    all {
                        languageSettings {
                            optIn("kotlinx.cinterop.ExperimentalForeignApi")
                        }
                    }
                }
            }
            cocoapods {
                ios.deploymentTarget = "15.2"
                podfile = rootProject.file("ios/Podfile")
                pod("Flutter")
            }
            jvmToolchain(17)
        }
        androidLibrary {
            compileSdk = 33
            defaultConfig {
                minSdk = 19
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }
}
