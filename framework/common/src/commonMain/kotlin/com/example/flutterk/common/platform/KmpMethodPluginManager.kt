package com.example.flutterk.common.platform

import com.example.flutterk.common.plugin.PlatformStringPlugin

internal object KmpMethodPluginManager {

    private val handlers = linkedSetOf<KmpMethodCallPlugin>()

    fun register(handler: KmpMethodCallPlugin) {
        handlers.add(handler)
    }

    fun dispose(handler: KmpMethodCallPlugin) {
        handlers.remove(handler)
    }

    fun setupPlugins(container: KmpFlutterContainer) {
        handlers.forEach { handler ->
            container.setMethodCall(handler)
        }
    }

    init {
        // TODO: di plugins
        register(PlatformStringPlugin())
    }
}