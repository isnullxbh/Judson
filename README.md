# Judson

Telegram bot for tracking packages

## Prerequisites

- JDK
- Gradle

## Run

To just run the app, go to the project's root directory and execute the following
command:

```shell
./gradlew run --args="<BOT_TOKEN>"
```

## Bundle the application

To bundle the application, go to the project's root directory and execute the following
command:

```shell
./gradlew build
```

If no error occur, Gradle will have produced an archive in the [following](build/distributions)
directory.

### Run

To run bundled application:

1. Unzip `Judson-<VERSION>.zip`.
2. Go to `Judson-<VERSION>/bin`.
3. Run `Judson` by passing the bot token.

## Author

Oleg E. Vorobiov <isnullxbh@gmail.com>
