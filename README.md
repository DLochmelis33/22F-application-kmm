# KMM application task: lock-free set

### Description

This project is built using standard Gradle structure, which means the source code can be found in [src/main/](src/main) and the tests are in [src/test/](src/test) folder.

For the main implementation, see [LockFreeStack.kt](src/main/kotlin/me/dl33/LockFreeStack.kt). This implementation is a linked list one, with `head` pointer being an atomic reference.

Tests for structure correctness are in [LockFreeStackValidityTest.kt](src/test/kotlin/me/dl33/LockFreeStackValidityTest.kt). Concurrency tests are written using [Lincheck](https://github.com/Kotlin/kotlinx-lincheck) and can be found in [LockFreeStackLincheckTest.kt](src/test/kotlin/me/dl33/LockFreeStackLincheckTest.kt).

### Running the tests

As usual with Gradle projects, you can either use the IDE 'test' option, or use the following command:
```bash
gradlew test
```

#### Note:
I never knew about the AtomicFU library and it's really handy! Much less boilerplate than with JVM AtomicReference.
