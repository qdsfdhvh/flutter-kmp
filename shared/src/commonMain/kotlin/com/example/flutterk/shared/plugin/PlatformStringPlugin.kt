package com.example.flutterk.shared.plugin

import com.example.flutterk.shared.platform.KmpMethodCall
import com.example.flutterk.shared.platform.KmpMethodCallPlugin
import com.example.flutterk.shared.platform.KmpMethodResult

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