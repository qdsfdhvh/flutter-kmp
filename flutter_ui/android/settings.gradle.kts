pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    fun flutterSdkPath(): String {
        val properties = java.util.Properties().apply {
            load(file("local.properties").inputStream())
        }
        val flutterSdkPath = properties.getProperty("flutter.sdk")
        assert(flutterSdkPath != null) { "flutter.sdk not set in local.properties" }
        return flutterSdkPath
    }
    settings.extra["flutterSdkPath"] = flutterSdkPath()

    includeBuild("${settings.extra["flutterSdkPath"]}/packages/flutter_tools/gradle")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}

include(":app")

includeBuild("../../") {
    dependencySubstitution {
        substitute(module("mine:kmp.shared")).using(project(":shared"))
    }
}

apply(from = "${settings.extra["flutterSdkPath"]}/packages/flutter_tools/gradle/app_plugin_loader.gradle")
