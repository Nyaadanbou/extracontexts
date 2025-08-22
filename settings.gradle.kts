pluginManagement {
    repositories {
        mavenLocal() // 为了导入 "nyaadanbou-repositories"
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("nyaadanbou-repository") version "0.0.1-snapshot"
}

dependencyResolutionManagement {
    repositories {
        maven("https://repo.mewcraft.cc/releases")
        maven("https://repo.mewcraft.cc/private") {
            credentials {
                username = providers.gradleProperty("nyaadanbou.mavenUsername").getOrElse("")
                password = providers.gradleProperty("nyaadanbou.mavenPassword").getOrElse("")
            }
        }
    }
    versionCatalogs {
        create("local") {
            from(files("gradle/local.versions.toml"))
        }
    }
}

rootProject.name = "extracontexts"
