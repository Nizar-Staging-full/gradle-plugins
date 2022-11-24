/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.publishing.publisher

import org.gradle.api.Project
import org.gradle.api.Task
import tech.antibytes.gradle.publishing.PublishingApiContract
import tech.antibytes.gradle.publishing.PublishingContract
import tech.antibytes.gradle.publishing.maven.MavenPublisher
import tech.antibytes.gradle.publishing.maven.MavenRepository

internal object PublisherSubProjectController : PublishingContract.PublisherController {
    private fun isApplicable(
        extension: PublishingContract.PublishingPluginExtension,
    ): Boolean {
        return extension.repositories.get().isNotEmpty() &&
            extension.packaging.orNull is PublishingApiContract.PackageConfiguration
    }

    override fun configure(
        project: Project,
        version: String,
        documentation: Task?,
        extension: PublishingContract.PublishingPluginExtension,
    ) {
        val registryConfigurations = extension.repositories.get()

        if (isApplicable(extension)) {
            MavenPublisher.configure(
                project = project,
                configuration = extension.packaging.get(),
                version = version,
                docs = documentation,
            )

            registryConfigurations.forEach { registry ->
                MavenRepository.configure(
                    project,
                    registry,
                    extension.dryRun.get(),
                )
            }
        }
    }
}
