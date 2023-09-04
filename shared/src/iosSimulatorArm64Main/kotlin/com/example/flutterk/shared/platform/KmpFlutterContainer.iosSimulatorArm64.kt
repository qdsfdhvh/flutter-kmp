package com.example.flutterk.shared.platform

import cocoapods.Flutter.FlutterMethodChannel
import cocoapods.Flutter.FlutterStandardMethodCodec
import platform.darwin.NSObject

internal actual fun setMethodCall(
    messenger: NSObject,
    plugin: KmpMethodCallPlugin,
) {
    FlutterMethodChannel(
        plugin.name,
        messenger,
        FlutterStandardMethodCodec.sharedInstance(),
    ).setMethodCallHandler(
        plugin.toIosHandler(),
    )
}
