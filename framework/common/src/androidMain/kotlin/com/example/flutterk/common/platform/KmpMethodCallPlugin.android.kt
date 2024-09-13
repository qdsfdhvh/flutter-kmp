package com.example.flutterk.common.platform

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

internal fun KmpMethodCallPlugin.toAndroidHandler(): MethodCallHandler =
    MethodCallHandler { call, result ->
        onMethodCall(call.toKmp(), result)
    }

private fun MethodCall.toKmp() = KmpMethodCall(
    method = method,
    arguments = arguments,
)

actual typealias KmpMethodResult = MethodChannel.Result
