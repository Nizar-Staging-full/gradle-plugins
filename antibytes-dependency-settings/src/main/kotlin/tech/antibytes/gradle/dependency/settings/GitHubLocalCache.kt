/*
 * Copyright (c) 2023 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.settings

import java.io.File
import org.gradle.caching.configuration.BuildCacheConfiguration

fun BuildCacheConfiguration.localGithub() {
    local {
        isEnabled = isGitHub()
        directory = File(
            ".gradle",
            "build-cache",
        )
        removeUnusedEntriesAfterDays = 3
    }
}
