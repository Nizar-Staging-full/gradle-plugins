/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.catalog.module.node

import tech.antibytes.gradle.dependency.catalog.NodeArtifact

internal object Production {
    val axios = NodeArtifact(id = "axios")
    val sqlJs = NodeArtifact(id = "sql.js")
}
