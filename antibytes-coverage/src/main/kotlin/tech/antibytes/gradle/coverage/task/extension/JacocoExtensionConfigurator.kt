/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.coverage.task.extension

import org.gradle.api.Project
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import tech.antibytes.gradle.coverage.AntibytesCoveragePluginExtension
import tech.antibytes.gradle.coverage.task.TaskContract

internal object JacocoExtensionConfigurator : TaskContract.JacocoExtensionConfigurator {
    override fun configure(project: Project, configuration: AntibytesCoveragePluginExtension) {
        val extension = project.extensions.getByType(JacocoPluginExtension::class.java)

        extension.toolVersion = configuration.jacocoVersion.get()
    }
}
