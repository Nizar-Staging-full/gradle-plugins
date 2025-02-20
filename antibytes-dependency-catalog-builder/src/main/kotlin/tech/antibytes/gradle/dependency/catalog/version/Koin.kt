/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.catalog.version

import tech.antibytes.gradle.dependency.config.GradleVersions

/**
 * [Koin](https://github.com/InsertKoinIO/koin)
 */
internal object Koin {
    private const val version = GradleVersions.koinCore

    const val annotations = GradleVersions.koinAnnotations
    const val core = version
    const val ktor = version
    const val slf4j = version
    const val test = version
    const val junit4 = version
    const val junit5 = version
}
