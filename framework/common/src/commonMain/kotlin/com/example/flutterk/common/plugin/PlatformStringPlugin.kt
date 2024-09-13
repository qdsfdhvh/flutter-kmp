package com.example.flutterk.common.plugin

import com.example.flutterk.common.platform.KmpMethodCall
import com.example.flutterk.common.platform.KmpMethodCallPlugin
import com.example.flutterk.common.platform.KmpMethodResult

class PlatformStringPlugin : KmpMethodCallPlugin {

    override val name: String
        get() = "plugin_codelab"

    override fun onMethodCall(call: KmpMethodCall, result: KmpMethodResult) {
        when (call.method) {
           "getPlatformVersion" -> {
               result.success("kmp version 1.1.1")
           }
        }
    }
}