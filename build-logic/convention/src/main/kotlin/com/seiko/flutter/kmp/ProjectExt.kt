package com.seiko.flutter.kmp

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

internal fun Project.kotlin(action: KotlinMultiplatformExtension.() -> Unit) =
    extensions.configure<KotlinMultiplatformExtension>(action)

internal fun Project.androidLibrary(action: LibraryExtension.() -> Unit) =
    extensions.configure<LibraryExtension>(action)

internal fun KotlinMultiplatformExtension.cocoapods(action: CocoapodsExtension.() -> Unit) =
    (this as ExtensionAware).extensions.configure<CocoapodsExtension>(action)