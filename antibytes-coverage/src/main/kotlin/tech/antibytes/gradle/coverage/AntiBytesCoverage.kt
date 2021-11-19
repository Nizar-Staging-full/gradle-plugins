/*
 * Copyright (c) 2021 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.coverage

import org.gradle.api.Plugin
import org.gradle.api.Project
import tech.antibytes.gradle.coverage.CoverageContract.CONSTANTS.EXTENSION_ID
import tech.antibytes.gradle.coverage.configuration.DefaultConfigurationProvider
import tech.antibytes.gradle.coverage.configuration.PlatformContextResolver.isKmp
import tech.antibytes.gradle.coverage.task.TaskController

class AntiBytesCoverage : Plugin<Project> {
    override fun apply(target: Project) {
        if (target.plugins.findPlugin("jacoco") == null) {
            target.plugins.apply("jacoco")
        }

        val extension = target.extensions.create(
            EXTENSION_ID,
            AntiBytesCoverageExtension::class.java,
            target
        )

        target.evaluationDependsOnChildren()

        target.afterEvaluate {
            if (isKmp(target) && extension.appendKmpJvmTask) {
                DefaultConfigurationProvider.createDefaultCoverageConfiguration(target)["jvm"]?.also {
                    extension.configurations.putIfAbsent("jvm", it)
                }
            }

            TaskController.configure(target, extension)
        }
    }
}
