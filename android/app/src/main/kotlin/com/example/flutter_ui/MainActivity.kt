package com.example.flutter_ui

import android.os.Bundle
import com.example.flutterk.common.platform.setupKmpPlugins
import io.flutter.embedding.android.FlutterActivity

class MainActivity: FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flutterEngine?.let {
            setupKmpPlugins(it.dartExecutor.binaryMessenger)
        }
    }
}
