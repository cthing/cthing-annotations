# ![C Thing Software](https://www.cthing.com/branding/CThingSoftware-57x60.png "C Thing Software") cthing-annotations

[![CI](https://github.com/cthing/cthing-annotations/actions/workflows/ci.yml/badge.svg)](https://github.com/cthing/cthing-annotations/actions/workflows/ci.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.cthing/cthing-annotations/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.cthing/cthing-annotations)
[![javadoc](https://javadoc.io/badge2/org.cthing/cthing-annotations/javadoc.svg)](https://javadoc.io/doc/org.cthing/cthing-annotations)

A library providing various annotations to enhance code quality and readability.

| Annotation                                                                                                                                 | Description                                                                                                                               |
|--------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| [AccessForTesting](https://javadoc.io/doc/org.cthing/cthing-annotations/latest/org/cthing/annotations/AccessForTesting.html)               | A marker annotation to indicate that a method, constructor or type is given increased access for testing purposes.                        |
| [NoCoverageGenerated](https://javadoc.io/doc/org.cthing/cthing-annotations/latest/org/cthing/annotations/NoCoverageGenerated.html)         | An annotation to indicate to test coverage tools that the annotated item should be excluded from analysis.                                |

## Usage
The library is available from [Maven Central](https://repo.maven.apache.org/maven2/org/cthing/cthing-annotations/) using the following Maven dependency:
```xml
<dependency>
    <groupId>org.cthing</groupId>
    <artifactId>cthing-annotations</artifactId>
    <version>2.0.0</version>
    <scope>provided</scope>
</dependency>
```
or the following Gradle dependency:
```kotlin
compileOnly("org.cthing:cthing-annotations:2.0.0")
```

### Building
The library is compiled for Java 17. If a Java 17 toolchain is not available, one will be downloaded.

Gradle is used to build the library:
```
./gradlew build
```
The Javadoc for the library can be generated by running:
```bash
./gradlew javadoc
```

### Releasing
This project is released on the [Maven Central repository](https://central.sonatype.com/artifact/org.cthing/cthing-annotations).
Perform the following steps to create a release.

- Commit all changes for the release
- In the `build.gradle.kts` file, edit the `ProjectVersion` object
    - Set the version for the release. The project follows [semantic versioning](https://semver.org/).
    - Set the build type to `BuildType.release`
- Commit the changes
- Wait until CI builds the release candidate
- Run the command `mkrelease cthing-annotations <version>`
- In a browser go to the [Maven Central Repository Manager](https://s01.oss.sonatype.org/)
- Log in
- Use the `Staging Upload` to upload the generated artifact bundle `cthing-annotations-bundle-<version>.jar`
- Click on `Staging Repositories`
- Once it is enabled, press `Release` to release the artifacts to Maven Central
- Log out
- Wait for the new release to be available on Maven Central
- In a browser, go to the project on GitHub
- Generate a release with the tag `<version>`
- In the build.gradle.kts file, edit the `ProjectVersion` object
    - Increment the version patch number
    - Set the build type to `BuildType.snapshot`
- Update the `CHANGELOG.md` with the changes in the release and prepare for next release changes
- Update the `Usage` section in the `README.md` with the latest artifact release version
- Commit these changes
