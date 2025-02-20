/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

/* ktlint-disable filename */
package tech.antibytes.gradle.coverage.configuration

import java.io.File

internal fun makePath(vararg path: String): String = makePath(path.toList())

internal fun makePath(path: List<String>): String = path.joinToString(File.separator.toString())
