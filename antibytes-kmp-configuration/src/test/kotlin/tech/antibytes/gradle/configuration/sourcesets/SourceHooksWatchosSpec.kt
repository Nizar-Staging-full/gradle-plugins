/*
 * Copyright (c) 2023 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.configuration.sourcesets

import com.appmattus.kotlinfixture.kotlinFixture
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.junit.jupiter.api.Test
import tech.antibytes.gradle.configuration.TestNamedProvider
import tech.antibytes.gradle.test.invokeGradleAction

class SourceHooksWatchosSpec {
    private val fixture = kotlinFixture()

    @Test
    fun `Given watchosx is called it delegates the given parameter to the KMP configuration`() {
        // Given
        val extension: KotlinMultiplatformExtension = mockk(relaxed = true)
        val prefix: String = fixture()
        val configuration: KotlinNativeTarget.() -> Unit = { }
        val watchosSimulator: KotlinSourceSet = mockk(relaxed = true)
        val watchos: KotlinSourceSet = mockk()
        val sourceSets: NamedDomainObjectContainer<KotlinSourceSet> = mockk()

        every { extension.sourceSets } returns sourceSets
        every { sourceSets.named(any()) } returns TestNamedProvider(watchos)
        invokeGradleAction(watchosSimulator, mockk()) { sourceSet ->
            sourceSets.named(any(), sourceSet)
        }

        // When
        extension.watchosx(prefix, configuration)

        // Then
        verify(exactly = 1) { extension.watchos(prefix, configuration) }
        verify(exactly = 1) { extension.watchosSimulatorArm64("${prefix}SimulatorArm64", configuration) }
        verify(exactly = 1) { sourceSets.named("${prefix}SimulatorArm64Main", any()) }
        verify(exactly = 1) { sourceSets.named("${prefix}SimulatorArm64Test", any()) }
        verify(exactly = 2) { watchosSimulator.dependsOn(watchos) }
    }

    @Test
    fun `Given legacyIos is called it delegates the given parameter to the KMP configuration`() {
        // Given
        val extension: KotlinMultiplatformExtension = mockk(relaxed = true)
        val prefix: String = fixture()
        val configuration: KotlinNativeTarget.() -> Unit = { }
        val watchosX86: KotlinSourceSet = mockk(relaxed = true)
        val watchos: KotlinSourceSet = mockk()
        val sourceSets: NamedDomainObjectContainer<KotlinSourceSet> = mockk()

        every { extension.sourceSets } returns sourceSets
        every { sourceSets.named(any()) } returns TestNamedProvider(watchos)
        invokeGradleAction(watchosX86, mockk()) { sourceSet ->
            sourceSets.named(any(), sourceSet)
        }

        // When
        extension.watchosWithLegacy(prefix, configuration)

        // Then
        verify(exactly = 1) { extension.watchos(prefix, configuration) }
        verify(exactly = 1) { extension.watchosX86("${prefix}X86", configuration) }
        verify(exactly = 1) { sourceSets.named("${prefix}X86Main", any()) }
        verify(exactly = 1) { sourceSets.named("${prefix}X86Test", any()) }
        verify(exactly = 2) { watchosX86.dependsOn(watchos) }
    }

    @Test
    fun `Given watchosxLegacy is called it delegates the given parameter to the KMP configuration`() {
        // Given
        val extension: KotlinMultiplatformExtension = mockk(relaxed = true)
        val prefix: String = fixture()
        val configuration: KotlinNativeTarget.() -> Unit = {}
        val watchosMix: KotlinSourceSet = mockk(relaxed = true)
        val watchos: KotlinSourceSet = mockk()
        val sourceSets: NamedDomainObjectContainer<KotlinSourceSet> = mockk()

        every { extension.sourceSets } returns sourceSets
        every { sourceSets.named(any()) } returns TestNamedProvider(watchos)
        invokeGradleAction(watchosMix, mockk()) { sourceSet ->
            sourceSets.named(any(), sourceSet)
        }

        // When
        extension.watchosxWithLegacy(prefix, configuration)

        // Then
        verify(exactly = 1) { extension.watchos(prefix, configuration) }
        verify(exactly = 1) { extension.watchosX86("${prefix}X86", configuration) }
        verify(exactly = 1) { sourceSets.named("${prefix}X86Main", any()) }
        verify(exactly = 1) { sourceSets.named("${prefix}X86Test", any()) }
        verify(exactly = 1) { extension.watchosSimulatorArm64("${prefix}SimulatorArm64", configuration) }
        verify(exactly = 1) { sourceSets.named("${prefix}SimulatorArm64Main", any()) }
        verify(exactly = 1) { sourceSets.named("${prefix}SimulatorArm64Test", any()) }
        verify(exactly = 4) { watchosMix.dependsOn(watchos) }
    }
}
