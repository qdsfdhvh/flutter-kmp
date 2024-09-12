package com.example.flutterk.shared.platform

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

internal fun KmpMethodCallPlugin.toAndroidHandler(): MethodCallHandler =
    MethodCallHandler { call, result ->
        onMethodCall(call, result)
    }

actual typealias KmpMethodCall = MethodCall

actual typealias KmpMethodResult = MethodChannel.Result
