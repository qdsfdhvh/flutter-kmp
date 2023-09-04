package com.example.flutterk.shared.platform

import platform.darwin.NSObject

actual class KmpFlutterContainer(
    private val messenger: NSObject,
) {
    actual fun setMethodCall(plugin: KmpMethodCallPlugin) {
        setMethodCall(messenger, plugin)
    }
}

internal expect fun setMethodCall(
    messenger: NSObject,
    plugin: KmpMethodCallPlugin,
)
