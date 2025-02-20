/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.catalog

internal fun List<String>.toDependencyName(): String = this.joinToString("-")

internal fun Any.toDependencyName(): String = this::class.simpleName!!.decapitalize()
