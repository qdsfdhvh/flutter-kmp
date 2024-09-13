package com.example.flutterk.common.platform

import io.flutter.plugin.common.BinaryMessenger

fun setupKmpPlugins(messenger: BinaryMessenger) {
    KmpMethodPluginManager.setupPlugins(KmpFlutterContainer(messenger))
}
