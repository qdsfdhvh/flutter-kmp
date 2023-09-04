package com.example.flutterk.shared.platform

import io.flutter.plugin.common.BinaryMessenger

fun setupKmpPlugins(messenger: BinaryMessenger) {
    KmpMethodPluginManager.setupPlugins(KmpFlutterContainer(messenger))
}
