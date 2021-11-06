/*
 * Copyright (c) 2021 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.publishing.publicApi

import tech.antibytes.gradle.publishing.PublishingApiContract

data class LicenseConfiguration(
    override val name: String,
    override val url: String,
    override val distribution: String
) : PublishingApiContract.LicenseConfiguration
