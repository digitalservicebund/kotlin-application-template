import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.TestSuiteType
import org.gradle.api.attributes.VerificationType
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.kotlin.dsl.*
import org.gradle.testing.jacoco.plugins.JacocoCoverageReport

plugins {
    java
    `jvm-test-suite`
    jacoco
    `jacoco-report-aggregation`
    id("com.adarshr.test-logger")
}

val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

testlogger { theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA }

testing {
    @Suppress("UnstableApiUsage")
    suites {
        val test by getting(JvmTestSuite::class) {
            testType.set(TestSuiteType.UNIT_TEST)
        }

        register("integrationTest", JvmTestSuite::class) {
            testType.set(TestSuiteType.INTEGRATION_TEST)
            dependencies {
                implementation(project())
            }
            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

tasks {
    check {
        dependsOn(
            testCodeCoverageReport,
            getByName("integrationTestCodeCoverageReport"),
            getByName("aggregateCodeCoverageReport"),
        )
    }
}

reporting {
    reports {
        @Suppress("UnstableApiUsage")
        withType(JacocoCoverageReport::class) {
            reportTask.configure {
                classDirectories.setFrom(
                    files(
                        classDirectories.files.map {
                            fileTree(it) {
                                exclude("**/ApplicationKt**")
                            }
                        },
                    ),
                )
            }
        }

        @Suppress("UnstableApiUsage")
        create<JacocoCoverageReport>("aggregateCodeCoverageReport") {
            testType.set(TestSuiteType.UNIT_TEST)
            reportTask {
                executionData.from(
                    configurations["aggregateCodeCoverageReportResults"]
                        .incoming.artifactView {
                            lenient(true)
                            withVariantReselection()
                            attributes {
                                attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.VERIFICATION))
                                attribute(TestSuiteType.TEST_SUITE_TYPE_ATTRIBUTE, objects.named(TestSuiteType.INTEGRATION_TEST))
                                attribute(VerificationType.VERIFICATION_TYPE_ATTRIBUTE, objects.named(VerificationType.JACOCO_RESULTS))
                                attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, ArtifactTypeDefinition.BINARY_DATA_TYPE)
                            }
                        }.files,
                )
            }
        }
    }
}
