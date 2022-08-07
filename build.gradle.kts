plugins {
    kotlin("jvm").version("1.7.0")
}

repositories {
    mavenLocal()
    mavenCentral()
}

ext {
    set("kotest.version", "5.4.1")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${property("kotest.version")}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${property("kotest.version")}")
    testImplementation("io.kotest:kotest-property-jvm:${property("kotest.version")}")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    withType<Jar> {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
        manifest {
            attributes(
                "Main-Class" to "edu.bach.kotlin.MainKt"
            )
        }
    }
}
