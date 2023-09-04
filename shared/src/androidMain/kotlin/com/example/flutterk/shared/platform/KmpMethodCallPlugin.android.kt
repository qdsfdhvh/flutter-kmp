package com.example.flutterk.shared.platform

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

internal fun KmpMethodCallPlugin.toAndroidHandler(): MethodCallHandler =
    MethodCallHandler { call, result ->
        onMethodCall(call.toKmp(), result.toKmp())
    }

private fun MethodCall.toKmp() = KmpMethodCall(
    method = method,
    arguments = arguments,
)

private fun MethodChannel.Result.toKmp() = object : KmpMethodResult {
    override fun success(result: Any) {
        this@toKmp.success(result)
    }

    override fun error(errorCode: String, errorMessage: String?, errorDetails: Any?) {
        this@toKmp.error(errorCode, errorMessage, errorDetails)
    }

    override fun notImplemented() {
        this@toKmp.notImplemented()
    }
}