package com.example.flutterk.shared.platform

import cocoapods.Flutter.FlutterMethodCall
import cocoapods.Flutter.FlutterMethodCallHandler
import cocoapods.Flutter.FlutterResult
import kotlinx.cinterop.convert
import platform.Foundation.NSCocoaErrorDomain
import platform.Foundation.NSError

internal fun KmpMethodCallPlugin.toIosHandler(): FlutterMethodCallHandler =
    object : FlutterMethodCallHandler {
        override fun invoke(call: FlutterMethodCall?, result: FlutterResult) {
            if (call == null) return
            this@toIosHandler.onMethodCall(call, result.toKmp())
        }
    }

actual typealias KmpMethodCall = FlutterMethodCall

actual interface KmpMethodResult {
    actual fun success(result: Any?)
    actual fun error(errorCode: String, errorMessage: String?, errorDetails: Any?)
    actual fun notImplemented()
}

private fun FlutterResult.toKmp() = object : KmpMethodResult {
    override fun success(result: Any?) {
        this@toKmp?.invoke(result)
    }

    override fun error(errorCode: String, errorMessage: String?, errorDetails: Any?) {
        this@toKmp?.invoke(
            NSError.errorWithDomain(
                NSCocoaErrorDomain,
                (errorCode.toIntOrNull() ?: -999).convert(),
                mapOf(
                    "errorMessage" to errorMessage,
                    "errorDetails" to errorDetails,
                )
            )
        )
    }

    override fun notImplemented() {
        this@toKmp?.invoke(cocoapods.Flutter.FlutterMethodNotImplemented)
    }
}
