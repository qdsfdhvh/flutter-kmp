package com.example.flutterk.common.platform

class KmpMethodCall(
    val method: String,
    val arguments: Any?,
)

expect interface KmpMethodResult {
    fun success(result: Any?)
    fun error(errorCode: String, errorMessage: String?, errorDetails: Any?)
    fun notImplemented()
}

interface KmpMethodCallPlugin {

    val name: String

    fun onMethodCall(call: KmpMethodCall, result: KmpMethodResult)
}
