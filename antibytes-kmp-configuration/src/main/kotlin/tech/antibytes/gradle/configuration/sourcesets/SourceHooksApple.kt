/*
 * Copyright (c) 2023 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.configuration.sourcesets

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

fun KotlinMultiplatformExtension.apple(
    name: String = "apple",
    configuration: KotlinNativeTarget.() -> Unit = { },
) {
    iosx(configuration = configuration)
    macos(configuration = configuration)
    tvosx(configuration = configuration)
    watchosx(configuration = configuration)

    val appleMain = sourceSets.maybeCreate("${name}Main")
    val appleTest = sourceSets.maybeCreate("${name}Test")

    val targets = setOf(
        "ios",
        "macos",
        "tvos",
        "watchos",
    )

    wireDependencies(
        main = appleMain,
        test = appleTest,
        targets = targets,
    )
}

fun KotlinMultiplatformExtension.appleWithLegacy(
    name: String = "apple",
    configuration: KotlinNativeTarget.() -> Unit = { },
) {
    iosxWithLegacy(configuration = configuration)
    macos(configuration = configuration)
    tvosx(configuration = configuration)
    watchosxWithLegacy(configuration = configuration)

    val appleMain = sourceSets.maybeCreate("${name}Main")
    val appleTest = sourceSets.maybeCreate("${name}Test")

    val targets = setOf(
        "ios",
        "macos",
        "tvos",
        "watchos",
    )

    wireDependencies(
        main = appleMain,
        test = appleTest,
        targets = targets,
    )
}
