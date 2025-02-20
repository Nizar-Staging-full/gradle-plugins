/*
 * Copyright (c) 2023 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.settings

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.ClearEnvironmentVariable
import org.junitpioneer.jupiter.SetEnvironmentVariable

class GitHubHomeDirSpec {
    @Test
    @ClearEnvironmentVariable(key = "GITHUB")
    fun `Given gitHubHomeDir it returns an empty String if no homeDir was found`() {
        // When
        val actual = gitHubHomeDir()

        // Then
        assertEquals(
            actual = actual,
            expected = "",
        )
    }

    @Test
    @SetEnvironmentVariable(key = "GITHUB", value = "/User/runner/worker/...")
    fun `Given gitHubHomeDir it returns the path to the homeDir if homeDir was found`() {
        // When
        val actual = gitHubHomeDir()

        // Then
        assertEquals(
            actual = actual,
            expected = "/User/runner/",
        )
    }
}
