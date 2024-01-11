import com.github.jk1.license.filter.LicenseBundleNormalizer
import java.io.File

plugins {
    id("com.github.jk1.dependency-license-report")
}

licenseReport {
// If there's a new dependency with a yet unknown license causing this task to fail
// the license(s) will be listed in build/reports/dependency-license/dependencies-without-allowed-license.json
    allowedLicensesFile = File("$projectDir/allowed-licenses.json")
    filters = arrayOf(
        // With second arg true we get the default transformations:
        // https://github.com/jk1/Gradle-License-Report/blob/7cf695c38126b63ef9e907345adab84dfa92ea0e/src/main/resources/default-license-normalizer-bundle.json
        LicenseBundleNormalizer(null, true),
    )
}
