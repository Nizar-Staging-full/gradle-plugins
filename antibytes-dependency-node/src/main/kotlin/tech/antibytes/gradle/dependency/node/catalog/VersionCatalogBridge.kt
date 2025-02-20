/*
 * Copyright (c) 2022 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache License, Version 2.0
 */

package tech.antibytes.gradle.dependency.node.catalog

import org.gradle.api.initialization.dsl.VersionCatalogBuilder
import tech.antibytes.gradle.dependency.node.NodeDependencyTransformerContract.Bridge
import tech.antibytes.gradle.dependency.node.NodeDependencyTransformerContract.NodeDependencies

internal object VersionCatalogBridge : Bridge {
    private enum class Type {
        PRODUCTION,
        DEVELOPMENT,
        PEER,
        OPTIONAL,
    }

    private fun String.toPropertyName(): String {
        return this.split('-', '.', '/', '@')
            .joinToString("") { part -> part.capitalize() }
            .decapitalize()
    }

    private fun VersionCatalogBuilder.addDependency(
        type: Type,
        moduleName: String,
        version: String,
    ) {
        library(
            "node-${moduleName.toPropertyName()}",
            "node-${type.name.toLowerCase()}",
            moduleName,
        ).version(version)
    }

    private fun VersionCatalogBuilder.addProductionDependencies(dependencies: NodeDependencies) {
        dependencies.production.forEach { (module, version) ->
            addDependency(Type.PRODUCTION, module, version)
        }
    }

    private fun VersionCatalogBuilder.addDevelopmentDependencies(dependencies: NodeDependencies) {
        dependencies.development.forEach { (module, version) ->
            addDependency(Type.DEVELOPMENT, module, version)
        }
    }

    private fun VersionCatalogBuilder.addPeerDependencies(dependencies: NodeDependencies) {
        dependencies.peer.forEach { (module, version) ->
            addDependency(Type.PEER, module, version)
        }
    }

    private fun VersionCatalogBuilder.addOptionalDependencies(dependencies: NodeDependencies) {
        dependencies.optional.forEach { (module, version) ->
            addDependency(Type.OPTIONAL, module, version)
        }
    }

    override fun addNodeDependencies(
        builder: VersionCatalogBuilder,
        dependencies: NodeDependencies,
    ) {
        builder.addProductionDependencies(dependencies)
        builder.addDevelopmentDependencies(dependencies)
        builder.addPeerDependencies(dependencies)
        builder.addOptionalDependencies(dependencies)
    }
}
