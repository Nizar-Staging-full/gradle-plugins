/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.catalog.version

import tech.antibytes.gradle.dependency.catalog.GradleBundleVersion
import tech.antibytes.gradle.dependency.config.GradleVersions

internal object Vendor {
    /**
     * [NodeJs](https://nodejs.org/en/)
     */
    const val node = GradleVersions.nodeJs

    val slf4j = SLF4J

    /**
     * [SLF4J](http://www.slf4j.org/)
     */
    internal object SLF4J {
        private const val version = GradleVersions.slf4j

        const val noop = version
        const val api = version
    }

    /**
     * [UUID](https://github.com/benasher44/uuid)
     */
    const val uuid = GradleVersions.uuidKmp

    /**
     * [MkDocs](https://plugins.gradle.org/plugin/ru.vyarus.mkdocs)
     */
    val mkdocs = GradleBundleVersion(GradleVersions.mkDocs)

    val test = Test

    internal object Test {
        /**
         * [Paparazzi](https://github.com/cashapp/paparazzi)
         */
        val paparazzi = GradleBundleVersion(GradleVersions.paparazzi)

        /**
         * [mockk](http://mockk.io)
         */
        const val mockk = GradleVersions.mockk

        val junit = JUnit

        internal object JUnit {
            /**
             * [JUnit5](https://github.com/junit-team/junit5/)
             */
            private const val version = GradleVersions.junit
            const val bom = version
            const val core = version
            const val parameterized = version
            const val legacyEngineJunit4 = version

            /**
             * [JUnit4](https://github.com/junit-team/junit4/)
             */
            const val junit4 = GradleVersions.junit4
        }

        /**
         * [Compiler Test](https://github.com/tschuchortdev/kotlin-compile-testing)
         */
        val compiler = Compiler

        internal object Compiler {
            private const val version = GradleVersions.compilerTest

            const val core = version
            const val ksp = version
        }
    }
}
