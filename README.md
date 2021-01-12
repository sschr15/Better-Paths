# Better Paths

This is a simple library built around Kotlin to support most `Files` methods
that operate on a `Path` object (`.readString()`, `.exists()`, etc).
The library also includes a couple other handy methods including one that
allows you to turn a `String` object to a `Path` as well as a couple operator
functions which allow you to get a path from `/`-separated strings.

There also is [an example file](src/example/kotlin/com/example/Example.kt)
which showcases some uses of this library.

## Use

Gradle build script (`build.gradle`):
```groovy
repositories {
    // ...
    maven {
        url = 'https://maven.concern.i.ng/'
    }
}

dependencies {
    // ...
    implementation 'sschr15.tools:better-paths:1.0.0'
}
```

Kotlin build script (`build.gradle.kts`):
```kotlin
repositories {
    // ...
    maven("https://maven.concern.i.ng/")
}

dependencies {
    // ...
    implementation("sschr15.tools:better-paths:1.0.0")
}
```
