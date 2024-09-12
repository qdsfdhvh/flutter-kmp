import org.jetbrains.kotlin.config.LanguageFeature

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.sqldelight)
    kotlin("native.cocoapods")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        all {
            languageSettings {
                enableLanguageFeature(LanguageFeature.ExpectActualClasses.name)
            }
        }
        commonMain {
            dependencies {
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines.extensions)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.sqldelight.android.driver)

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
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../flutter_ui/ios/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        pod("Flutter")
    }
}

android {
    namespace = "com.example.flutterk.shared"
    compileSdk = 33
    defaultConfig {
        minSdk = 19
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.flutterk.shared.db")
            srcDirs.setFrom("src/commonMain/sqldelight/app")
        }
    }
}
