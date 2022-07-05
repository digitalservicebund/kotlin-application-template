package de.bund.digitalservice.template

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices

@AnalyzeClasses(packages = ["de.bund.digitalservice"])
class ArchitectureFitnessTest {

    @ArchTest
    val `packages should be free of cycles` =
        slices().matching("de.bund.digitalservice.(**)").should().beFreeOfCycles()
}
