plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.bundles.logicPlugins)
}

gradlePlugin {
    plugins {
        register("flutterLibrary") {
            id = "app.flutter.library"
            implementationClass = "FlutterLibraryConventionPlugin"
        }
    }
}