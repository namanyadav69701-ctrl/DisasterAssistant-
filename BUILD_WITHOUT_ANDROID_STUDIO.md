# Build Disaster Assistant without Android Studio

You do **not** need Android Studio for this workflow. GitHub Actions builds the APK on GitHub's build machine.

## One-time setup

1. Create a free GitHub account if you do not already have one.
2. Create a new repository, for example `DisasterAssistant`.
3. Extract the ZIP supplied by ChatGPT.
4. Upload the **contents inside the `DisasterAssistant` folder** to the repository. Make sure `.github/workflows/build-apk.yml` is included.
5. Commit the files to the default branch.

## Build the APK

The workflow runs automatically after a push to `main` or `master`.

You can also run it manually:

1. Open the repository on GitHub.
2. Open **Actions**.
3. Select **Build Disaster Assistant APK**.
4. Choose **Run workflow**.
5. When the build completes, open the workflow run.
6. Under **Artifacts**, download **DisasterAssistant-APK**.
7. Extract the downloaded artifact ZIP. It contains `DisasterAssistant-debug.apk`.

## Install on your Android phone

1. Transfer `DisasterAssistant-debug.apk` to your phone if you built it on a computer.
2. Open the APK on the phone.
3. Android may ask you to allow installation from the browser/file-manager you used. Only enable this for the app you trust and turn it off again afterward if you prefer.
4. Install **Disaster Assistant**.

This is a **debug APK for testing**, not a Play Store release build.

## Current MVP

The current application contains:

- Home screen
- Survival Mode
- Offline simulation switch
- Room/SQLite emergency protocol database
- Deterministic local emergency classifier used as a clearly labelled Stage-1 mock AI
- Structured emergency protocol retrieval
- Emergency scenario shortcuts
- Unit tests for the local classifier

## Safety architecture

The local AI/classifier does not generate medical procedures. It only interprets the user's message and selects a protocol ID. Emergency guidance is read from the structured local database. The included protocol text is demo content and requires professional review before real-world emergency use.

## Later local LLM integration

`LocalAIEngine` is the replacement boundary. A future `LlamaCppLocalAIEngine` can use JNI + llama.cpp + a GGUF model without changing the Compose screens or Room emergency database.
