package com.example.flutterk.common.platform

import cocoapods.Flutter.FlutterMethodChannel
import cocoapods.Flutter.FlutterStandardMethodCodec
import platform.darwin.NSObject

actual class KmpFlutterContainer(
    private val messenger: NSObject,
) {
    actual fun setMethodCall(plugin: KmpMethodCallPlugin) {
        FlutterMethodChannel(
            plugin.name,
            messenger,
            FlutterStandardMethodCodec.sharedInstance(),
        ).setMethodCallHandler(
            plugin.toIosHandler(),
        )
    }
}
