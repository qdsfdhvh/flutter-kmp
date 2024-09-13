package com.example.flutterk.common.platform

import platform.darwin.NSObject

fun setupKmpPlugins(messenger: NSObject) {
    KmpMethodPluginManager.setupPlugins(KmpFlutterContainer(messenger))
}
