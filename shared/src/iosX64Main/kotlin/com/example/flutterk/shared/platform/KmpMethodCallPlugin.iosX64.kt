package com.example.flutterk.shared.platform

import cocoapods.Flutter.FlutterMethodCall
import cocoapods.Flutter.FlutterMethodCallHandler
import cocoapods.Flutter.FlutterMethodNotImplemented
import cocoapods.Flutter.FlutterResult
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.Foundation.NSCocoaErrorDomain
import platform.Foundation.NSError

internal fun KmpMethodCallPlugin.toIosHandler(): FlutterMethodCallHandler =
    object : FlutterMethodCallHandler {
        override fun invoke(call: FlutterMethodCall?, result: FlutterResult) {
            if (call == null) return
            this@toIosHandler.onMethodCall(call.toKmp(), result.toKmp())
        }
    }

private fun FlutterMethodCall.toKmp() = KmpMethodCall(
    method = method,
    arguments = arguments,
)

@Suppress("UNREACHABLE_CODE")
@OptIn(ExperimentalForeignApi::class)
private fun FlutterResult.toKmp() = object : KmpMethodResult {
    override fun success(result: Any) {
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
        this@toKmp?.invoke(FlutterMethodNotImplemented)
    }
}
