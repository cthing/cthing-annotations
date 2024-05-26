# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [unreleased]

### Added

- [Dependency analysis Gradle plugin](https://github.com/autonomousapps/dependency-analysis-gradle-plugin)
- The `check` task now depends on the `buildHealth` task and will fail the build on health violations

### Changed

- Changed JSR-305 dependency from `implementation` to `api`
- Use cthing-projectversion library `ProjectVersion` object for project version

## [1.0.0] - 2023-12-22

### Added

- First release

[unreleased]: https://github.com/cthing/xmlwriter/compare/1.0.0...HEAD
[1.0.0]: https://github.com/cthing/xmlwriter/releases/tag/1.0.0
