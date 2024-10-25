# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [unreleased]

## [2.0.0] - 2024-10-25

### Removed

- The annotation `PackageNonnullByDefault` has been removed. Clients should use the `NullMarked` annotation
  provided by the [JSpecify](https://jspecify.dev/) library.
- The dependency on JSR 305 has been removed. Clients should use the [JSpecify library](https://jspecify.dev/)
  for `null` checking.

### Added

- The build now uses the [dependency-analysis-gradle-plugin](https://github.com/autonomousapps/dependency-analysis-gradle-plugin)
- The `check` task now depends on the `buildHealth` task and will fail the build on health violations

### Changed

- Use the [cthing-projectversion](https://github.com/cthing/cthing-projectversion) library
  [ProjectVersion](https://javadoc.io/doc/org.cthing/cthing-projectversion/latest/org/cthing/projectversion/ProjectVersion.html) 
  object for the project version

## [1.0.0] - 2023-12-22

### Added

- First release

[unreleased]: https://github.com/cthing/xmlwriter/compare/2.0.0...HEAD
[2.0.0]: https://github.com/cthing/xmlwriter/releases/tag/2.0.0
[1.0.0]: https://github.com/cthing/xmlwriter/releases/tag/1.0.0
