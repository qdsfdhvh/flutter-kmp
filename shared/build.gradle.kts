plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.sqldelight)
    kotlin("native.cocoapods")
}

kotlin {
    androidTarget()
    // jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines.extensions)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.android.driver)

                val flutterEngineVersion = "fb483d79c8b3d89555d8c876058d6d4b862d31fd"
                compileOnly("io.flutter:flutter_embedding_debug:1.0.0-$flutterEngineVersion")
                compileOnly("io.flutter:arm64_v8a_debug:1.0.0-$flutterEngineVersion")
                compileOnly("io.flutter:armeabi_v7a_debug:1.0.0-$flutterEngineVersion")
                compileOnly("io.flutter:x86_64_debug:1.0.0-$flutterEngineVersion")
                compileOnly("io.flutter:x86_debug:1.0.0-$flutterEngineVersion")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        // podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        specRepos {
            url("https://cdn.cocoapods.org/")
        }
        pod(
            name = "Flutter",
        )
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
