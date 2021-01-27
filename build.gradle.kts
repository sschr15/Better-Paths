plugins {
    java
    kotlin("jvm") version "1.4.21"
    `maven-publish`
}

group = "sschr15.tools"
version = "1.1.0"

sourceSets {
    create("example") {
        java {
            val main = sourceSets.getByName("main")
            compileClasspath += main.compileClasspath + main.output
            runtimeClasspath += main.runtimeClasspath + main.output
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

val sourceJar = task("sourceJar", Jar::class) {
    dependsOn(tasks["classes"])
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components.getByName("java"))
            artifact(sourceJar)
        }
    }

    repositories {
        maven("C:/Users/steve/maven")
//        mavenLocal()
    }
}
