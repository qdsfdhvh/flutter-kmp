plugins {
    id("app.flutter.library")
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    sourceSets {
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
            }
        }
    }
    cocoapods {
        summary = "Some description for the Common Module"
        homepage = "Link to the Common Module homepage"
        version = "1.0"
        framework {
            baseName = "common"
            isStatic = true
        }
    }
}

android {
    namespace = "com.example.flutterk.shared"
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.flutterk.common.db")
            srcDirs.setFrom("src/commonMain/sqldelight/app")
        }
    }
}
