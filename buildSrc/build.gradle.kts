plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(plugin(libs.plugins.version.catalog.update))
    implementation(plugin(libs.plugins.versions))
}

fun plugin(provider: Provider<PluginDependency>) = with(provider.get()) {
    "$pluginId:$pluginId.gradle.plugin:$version"
}
