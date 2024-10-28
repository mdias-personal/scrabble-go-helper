# Scrabble Go Helper app

## Purpose

The purpose of this application is to assist players while playing a game of scrabble. The app takes in a list of letters and provides the user with a list of scored
words that can be played in the game. Clicking on the resulting scored words searches google for the words defintion.

## Architectural Highlights:
 * mix of kotlin and java backend
 * android xml frontend

The app is unpublished, but can be used by loading it onto an android phone via [Android Studio](https://developer.android.com/studio)

## Plugins

The mobile app contains the following plugins:

  * [spotless-gradle-plugin](https://github.com/diffplug/spotless)

    Formats the files in the codebase. Upon committing code, spotless is run against any files to be committed.

```bash
    ./gradlew spotlessApply
```

### Building the application

The project uses [gradle](https://gradle.org/) as a build tool.

To build the application run:

```bash
  ./gradlew build
```

### Running the application

The app is run and loaded onto android phones via the Android Studio IDE.

## License

This project is the intellectual property of michael dias

