plugins {
    alias(libs.plugins.kotlin.spring)
    id("de.bund.digitalservice.license-report-conventions")
    id("de.bund.digitalservice.kotlin-conventions")
    id("de.bund.digitalservice.sonar-conventions")
    id("de.bund.digitalservice.spotless-conventions")
    id("de.bund.digitalservice.spring-boot-image-conventions")
    id("de.bund.digitalservice.test-conventions")
    id("de.bund.digitalservice.version-catalog-conventions")
}

group = "de.bund.digitalservice"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation(platform(libs.kotlin.bom))
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.reactor.kotlin.extensions)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlinx.coroutines.reactor)

    // CVE-2023-6378 / CVE-2023-6481
    implementation(libs.logback.classic)
    implementation(libs.logback.core)

    // CVE-2022-1471
    implementation(libs.snakeyaml)

    // CVE-2023-34062
    implementation(libs.reactor.netty.http)

    developmentOnly(libs.spring.boot.devtools)
}

testing {
    @Suppress("UnstableApiUsage")
    suites {
        getByName<JvmTestSuite>("test") {
            useJUnitJupiter()
            dependencies {
                implementation(libs.spring.boot.starter.test) {
                    exclude("org.mockito", "mockito-core")
                    because("Use MockK instead of Mockito since it is better suited for Kotlin")
                }
                implementation(libs.springmockk)
                implementation(libs.reactor.test)
                implementation(libs.spring.security.test)
                implementation(libs.archunit.junit5)
            }
        }

        getByName<JvmTestSuite>("integrationTest") {
            dependencies {
                implementation(libs.spring.boot.starter.test) {
                    exclude("org.mockito", "mockito-core")
                    because("Use MockK instead of Mockito since it is better suited for Kotlin")
                }
                implementation(libs.springmockk)
                implementation(libs.reactor.test)
                implementation(libs.spring.security.test)
            }
        }
    }
}
