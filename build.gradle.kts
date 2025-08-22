import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    `java-library`
    id("cc.mewcraft.libraries-repository") version "0.0.5-snapshot"
    alias(local.plugins.shadow)
    alias(local.plugins.pluginyml.paper)
}

group = "me.lucko"
version = "2.0"
description = "Provides a number of extra contexts for LuckPerms."

dependencies {
    // server api
    compileOnly(local.paper)

    // libs that present as other plugins
    compileOnly(local.luckperms)
    compileOnly(local.placeholderapi)

    // libs to be shaded
    implementation(local.worldguardwrapper)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveClassifier.set("")
        dependencies {
            exclude("META-INF/maven/**")
            exclude("META-INF/LICENSE*")
            exclude("META-INF/NOTICE*")
        }
    }
}

java {
    withSourcesJar()
}

paper {
    main = "me.lucko.extracontexts.ExtraContextsPlugin"
    name = "ExtraContexts"
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
