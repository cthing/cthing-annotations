import com.github.spotbugs.snom.Effort
import com.github.spotbugs.snom.Confidence
import org.cthing.projectversion.BuildType
import org.cthing.projectversion.ProjectVersion
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

repositories {
    mavenCentral()
}

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    `java-library`
    checkstyle
    `maven-publish`
    signing
    alias(libs.plugins.cthingVersioning)
    alias(libs.plugins.dependencyAnalysis)
    alias(libs.plugins.spotbugs)
    alias(libs.plugins.versions)
}

version = ProjectVersion("2.0.1", BuildType.snapshot)
group = "org.cthing"
description = "A library providing various annotations to enhance code quality and readability."

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

dependencies {
    spotbugsPlugins(libs.spotbugsContrib)
}

checkstyle {
    toolVersion = libs.versions.checkstyle.get()
    isIgnoreFailures = false
    configFile = file("dev/checkstyle/checkstyle.xml")
    configDirectory = file("dev/checkstyle")
    isShowViolations = true
}

spotbugs {
    toolVersion = libs.versions.spotbugs
    ignoreFailures = false
    effort = Effort.MAX
    reportLevel = Confidence.MEDIUM
    excludeFilter = file("dev/spotbugs/suppressions.xml")
}

dependencyAnalysis {
    issues {
        all {
            onAny {
                severity("fail")
            }
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks {
    withType<JavaCompile> {
        options.release = libs.versions.java.get().toInt()
        options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-options", "-Werror"))
    }

    withType<Jar> {
        manifest.attributes(mapOf("Implementation-Title" to project.name,
                                  "Implementation-Vendor" to "C Thing Software",
                                  "Implementation-Version" to project.version))
    }

    withType<Javadoc> {
        val year = SimpleDateFormat("yyyy", Locale.ENGLISH).format(Date())
        with(options as StandardJavadocDocletOptions) {
            breakIterator(false)
            encoding("UTF-8")
            bottom("Copyright &copy; $year C Thing Software")
            memberLevel = JavadocMemberLevel.PUBLIC
            outputLevel = JavadocOutputLevel.QUIET
        }
    }

    check {
        dependsOn(buildHealth)
    }

    spotbugsMain {
        reports.create("html").required = true
    }

    withType<GenerateModuleMetadata> {
        enabled = false
    }

    dependencyUpdates {
        revision = "release"
        gradleReleaseChannel = "current"
        outputFormatter = "plain,xml,html"
        outputDir = layout.buildDirectory.dir("reports/dependencyUpdates").get().asFile.absolutePath

        rejectVersionIf {
            isNonStable(candidate.version)
        }
    }
}

val sourceJar by tasks.registering(Jar::class) {
    from(project.sourceSets["main"].allSource)
    archiveClassifier = "sources"
}

val javadocJar by tasks.registering(Jar::class) {
    from(tasks.getByName("javadoc"))
    archiveClassifier = "javadoc"
}

publishing {
    publications {
        register("jar", MavenPublication::class) {
            from(components["java"])

            artifact(sourceJar)
            artifact(javadocJar)

            pom {
                name = project.name
                description = project.description
                url = "https://github.com/cthing/${project.name}"
                licenses {
                    license {
                        name = "Apache-2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                    }
                }
                developers {
                    developer {
                        id = "baron"
                        name = "Baron Roberts"
                        email = "baron@cthing.com"
                        organization = "C Thing Software"
                        organizationUrl = "https://www.cthing.com"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/cthing/${project.name}.git"
                    developerConnection = "scm:git:git@github.com:cthing/${project.name}.git"
                    url = "https://github.com/cthing/${project.name}"
                }
                issueManagement {
                    system = "GitHub Issues"
                    url = "https://github.com/cthing/${project.name}/issues"
                }
            }
        }
    }

    val repoUrl = if ((version as ProjectVersion).isSnapshotBuild)
        findProperty("cthing.nexus.snapshotsUrl") else findProperty("cthing.nexus.candidatesUrl")
    if (repoUrl != null) {
        repositories {
            maven {
                name = "CThingMaven"
                setUrl(repoUrl)
                credentials {
                    username = property("cthing.nexus.user") as String
                    password = property("cthing.nexus.password") as String
                }
            }
        }
    }
}

if (hasProperty("signing.keyId") && hasProperty("signing.password") && hasProperty("signing.secretKeyRingFile")) {
    signing {
        sign(publishing.publications["jar"])
    }
}
