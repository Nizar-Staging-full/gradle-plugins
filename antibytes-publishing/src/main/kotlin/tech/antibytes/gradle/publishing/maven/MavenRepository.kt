/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.publishing.maven

import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.publish.PublishingExtension
import tech.antibytes.gradle.publishing.PublishingApiContract
import tech.antibytes.gradle.publishing.PublishingApiContract.MavenRepositoryConfiguration
import tech.antibytes.gradle.publishing.PublishingApiContract.RepositoryConfiguration
import tech.antibytes.gradle.publishing.publisher.PublisherContract

internal object MavenRepository : PublisherContract.MavenRepository {
    private fun useCredentials(
        configuration: RepositoryConfiguration<out Any>,
        dryRun: Boolean,
    ): Boolean {
        return configuration is MavenRepositoryConfiguration &&
            !dryRun &&
            configuration.username != null
    }

    private fun getUrl(
        project: Project,
        configuration: RepositoryConfiguration<out Any>,
        dryRun: Boolean,
    ): Any {
        val localBasePath = "file://${project.rootProject.buildDir.absolutePath}/${configuration.name}"

        return when {
            configuration is PublishingApiContract.GitRepositoryConfiguration -> "$localBasePath/${configuration.gitWorkDirectory}"
            dryRun -> "$localBasePath/dryRun"
            else -> configuration.url
        }
    }

    private fun setRepository(
        project: Project,
        repository: MavenArtifactRepository,
        configuration: RepositoryConfiguration<out Any>,
        dryRun: Boolean,
    ) {
        repository.name = configuration.name.capitalize()
        repository.setUrl(
            getUrl(
                project,
                configuration,
                dryRun,
            ),
        )

        if (useCredentials(configuration, dryRun)) {
            repository.credentials {
                username = configuration.username
                password = configuration.password
            }
        }
    }

    override fun configure(
        project: Project,
        configuration: RepositoryConfiguration<out Any>,
        dryRun: Boolean,
    ) {
        project.extensions.configure(PublishingExtension::class.java) {
            repositories {
                repositories.maven {
                    setRepository(
                        project,
                        this,
                        configuration,
                        dryRun,
                    )
                }
            }
        }
    }
}
