/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.catalog.module.square

import tech.antibytes.gradle.dependency.catalog.MavenArtifact
import tech.antibytes.gradle.dependency.catalog.module.Square

internal object KotlinPoet {
    val core = MavenArtifact(
        group = Square.domain,
        id = "kotlinpoet",
    )
    val ksp = MavenArtifact(
        group = Square.domain,
        id = "kotlinpoet-ksp",
    )
}
