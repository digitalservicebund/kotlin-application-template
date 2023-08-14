plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(plugin(libs.plugins.dependency.license.report))
    implementation(plugin(libs.plugins.sonarqube))
    implementation(plugin(libs.plugins.spotless))
    implementation(plugin(libs.plugins.version.catalog.update))
    implementation(plugin(libs.plugins.versions))
}

fun plugin(provider: Provider<PluginDependency>) = with(provider.get()) {
    "$pluginId:$pluginId.gradle.plugin:$version"
}
