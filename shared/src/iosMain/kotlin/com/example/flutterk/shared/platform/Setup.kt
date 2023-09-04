package com.example.flutterk.shared.platform

import platform.darwin.NSObject

fun setupKmpPlugins(messenger: NSObject) {
    KmpMethodPluginManager.setupPlugins(KmpFlutterContainer(messenger))
}
