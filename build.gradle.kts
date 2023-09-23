import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("cc.mewcraft.repo-conventions")
    id("cc.mewcraft.java-conventions")
    id("cc.mewcraft.deploy-conventions")
    alias(libs.plugins.pluginyml.paper)
}

project.ext.set("name", "ExtraContexts")

group = "me.lucko"
version = "2.0"
description = "Provides a number of extra contexts for LuckPerms."

dependencies {
    // server api
    compileOnly(libs.server.paper)

    // libs that present as other plugins
    compileOnly(libs.luckperms)
    compileOnly(libs.papi)

    // libs to be shaded
    implementation(libs.worldguardwrapper)
}

paper {
    main = "me.lucko.extracontexts.ExtraContextsPlugin"
    name = project.ext.get("name") as String
    version = "${project.version}"
    description = project.description
    apiVersion = "1.19"
    authors = listOf("Luck")

    serverDependencies {
        register("LuckPerms") {
            required = true
            joinClasspath = true
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }
        register("WorldGuard") {
            required = false
            joinClasspath = true
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }
        register("PlaceholderAPI") {
            required = false
            joinClasspath = true
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }
    }
}
