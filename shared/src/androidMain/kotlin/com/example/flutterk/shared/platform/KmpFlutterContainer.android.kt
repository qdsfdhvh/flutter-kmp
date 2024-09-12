package com.example.flutterk.shared.platform

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

actual class KmpFlutterContainer(
    private val messenger: BinaryMessenger,
) {
    actual fun setMethodCall(plugin: KmpMethodCallPlugin) {
        MethodChannel(
            messenger,
            plugin.name,
        ).setMethodCallHandler(
            plugin.toAndroidHandler(),
        )
    }
}