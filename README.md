# Disaster Assistant

A small offline-first Android MVP that can be built completely through GitHub Actions. Android Studio is not required for the GitHub build workflow.

## Current MVP

- Kotlin
- Jetpack Compose
- Room / SQLite
- Coroutines
- Home screen
- Survival Mode
- Offline simulation
- Local emergency protocol retrieval
- Replaceable `LocalAIEngine`
- Deterministic local mock classifier for the first stage

The mock classifier does **not** generate medical procedures. It only classifies the emergency. Emergency guidance is retrieved from the local Room database.

> The bundled emergency protocol text is demo content and requires professional review before any real-world emergency deployment.

## Build APK on GitHub

1. Extract the ZIP.
2. Upload the **contents** of the extracted folder to the root of a GitHub repository.
3. Confirm `.github/workflows/build-apk.yml` exists in the repository.
4. Open **Actions → Build Disaster Assistant APK**.
5. Run the workflow if it has not started automatically.
6. After a successful build, download the **DisasterAssistant-APK** artifact.
7. Extract the artifact to get `DisasterAssistant.apk`.

If the build fails, download **DisasterAssistant-Build-Log** and inspect or share `build-log.txt`.

## Build compatibility

This project intentionally uses conservative, widely available versions:

- Android Gradle Plugin 8.5.2
- Gradle 8.7
- Kotlin 1.9.24
- Java 17
- compileSdk / targetSdk 34
- Room 2.6.1
- Compose BOM 2024.06.00

These versions are chosen to reduce build compatibility problems in GitHub Actions.
