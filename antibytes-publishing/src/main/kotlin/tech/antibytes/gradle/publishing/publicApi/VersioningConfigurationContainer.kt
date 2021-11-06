/*
 * Copyright (c) 2021 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.publishing.publicApi

import tech.antibytes.gradle.publishing.PublishingApiContract

data class VersioningConfigurationContainer(
    override val releasePattern: Regex = "main|release/.*".toRegex(),
    override val featurePattern: Regex = "feature/(.*)".toRegex(),
    override val dependencyBotPattern: Regex = "dependabot/(.*)".toRegex(),
    override val issuePattern: Regex? = null,
    override val versionPrefix: String = "v",
    override val normalization: Set<String> = emptySet()
) : PublishingApiContract.VersioningConfiguration
