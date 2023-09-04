package com.example.flutterk.shared.platform

class KmpMethodCall(
    val method: String,
    val arguments: Any?,
)

interface KmpMethodResult {
    fun success(result: Any)
    fun error(errorCode: String, errorMessage: String?, errorDetails: Any?)
    fun notImplemented()
}

interface KmpMethodCallPlugin {

    val name: String

    fun onMethodCall(call: KmpMethodCall, result: KmpMethodResult)
}
