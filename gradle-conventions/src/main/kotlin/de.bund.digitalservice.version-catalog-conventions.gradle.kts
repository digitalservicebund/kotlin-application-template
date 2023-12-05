plugins {
    id("nl.littlerobots.version-catalog-update")
    id("com.github.ben-manes.versions")
}

tasks {
    withType(com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask::class) {
        fun isStable(version: String): Boolean {
            val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
            val regex = "^[0-9,.v-]+(-r)?$".toRegex()
            return stableKeyword || regex.matches(version)
        }
        gradleReleaseChannel = "current"
        revision = "release"
        rejectVersionIf { !isStable(candidate.version) }
    }
}

versionCatalogUpdate {
    keep {
        versions.set(listOf("jacoco"))
        keepUnusedPlugins.set(true)
    }
}
