plugins {
    id("fabric-loom")
    id("maven-publish")
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}
base {
    val archivesBaseNameTwo: String by project
    archivesBaseName = archivesBaseNameTwo
}
val modVersion: String by project
version = modVersion
val mavenGroup: String by project
group = mavenGroup
minecraft {
}
repositories {
    maven("https://maven.fabricmc.net/")
}
dependencies {
    //to change the versions see the gradle.properties file
    val minecraftVersion: String by project
    minecraft("com.mojang:minecraft:$minecraftVersion")
    val yarnMappings: String by project
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    val loaderVersion: String by project
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    // Fabric API. This is technically optional, but you probably want it anyway.
    val fabricVersion: String by project
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    val fabricKotlinVersion: String by project
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")
    // PSA: Some older mods, compiled on Loom 0.2.1, might have outdated Maven POMs.
    // You may need to force-disable transitiveness on them.
}
// ensure that the encoding is set to UTF-8, no matter what the system default is
// this fixes some edge cases with special characters not displaying correctly
// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
// if it is present.
// If you remove this task, sources will not be generated.
tasks {
    jar {
        from("LICENSE")
    }
    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        inputs.property("version", project.version)
        from(sourceSets["main"].resources.srcDirs) {
            include("fabric.mod.json")
            expand(mutableMapOf("version" to project.version))
        }
        from(sourceSets["main"].resources.srcDirs) {
            exclude("fabric.mod.json")
        }
    }
    val javaVersion = JavaVersion.VERSION_1_8.toString()
    compileKotlin {
        kotlinOptions {
            jvmTarget = javaVersion
        }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }
    artifacts {
        archives(sourcesJar)
    }
}