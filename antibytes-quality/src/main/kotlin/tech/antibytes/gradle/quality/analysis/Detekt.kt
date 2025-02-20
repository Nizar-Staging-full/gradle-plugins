/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.quality.analysis

import io.gitlab.arturbosch.detekt.Detekt as DetektTask
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import tech.antibytes.gradle.quality.Configurator
import tech.antibytes.gradle.quality.QualityApiContract.CodeAnalysisConfiguration
import tech.antibytes.gradle.quality.QualityContract.Extension
import tech.antibytes.gradle.quality.config.MainConfig

internal object Detekt : Configurator() {
    private fun Project.configureDetekt(configuration: CodeAnalysisConfiguration) {
        extensions.configure(DetektExtension::class.java) {
            toolVersion = MainConfig.detektVersion
            buildUponDefaultConfig = true
            allRules = false
            config = configuration.configurationFiles
            baseline = configuration.baselineFile
            source = configuration.sourceFiles
            autoCorrect = configuration.autoCorrection
        }
    }

    private fun Project.configureDetektTask(configuration: CodeAnalysisConfiguration) {
        tasks.withType(DetektTask::class.java).configureEach {
            jvmTarget = configuration.jvmVersion
            exclude(*configuration.exclude.toTypedArray())
            reports.apply {
                html.required.set(configuration.reports.html)
                xml.required.set(configuration.reports.xml)
                sarif.required.set(configuration.reports.sarif)
                txt.required.set(configuration.reports.txt)
            }
        }
    }

    private fun Project.configureBaseline(configuration: CodeAnalysisConfiguration) {
        tasks.withType(DetektCreateBaselineTask::class.java).configureEach {
            jvmTarget = configuration.jvmVersion
            exclude(*configuration.excludeBaseline.toTypedArray())
        }
    }

    override fun configure(project: Project, configuration: Extension) {
        configuration.codeAnalysis.orNull.applyIfNotNull { codeAnalysisConfiguration ->
            project.plugins.apply("io.gitlab.arturbosch.detekt")

            project.configureDetekt(codeAnalysisConfiguration)
            project.configureDetektTask(codeAnalysisConfiguration)
            project.configureBaseline(codeAnalysisConfiguration)
        }
    }
}
