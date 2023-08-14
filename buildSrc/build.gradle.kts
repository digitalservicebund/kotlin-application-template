plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(plugin(libs.plugins.dependency.license.report))
    implementation(plugin(libs.plugins.kotlin.jvm))
    implementation(plugin(libs.plugins.sonarqube))
    implementation(plugin(libs.plugins.spotless))
    implementation(plugin(libs.plugins.test.logger))
    implementation(plugin(libs.plugins.version.catalog.update))
    implementation(plugin(libs.plugins.versions))
}

fun plugin(provider: Provider<PluginDependency>) = with(provider.get()) {
    "$pluginId:$pluginId.gradle.plugin:$version"
}
