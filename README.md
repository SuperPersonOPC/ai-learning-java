# ai-learning-java

Starter project for learning core Java concepts with simple, tested examples.

## What is included

- Maven-based Java 17 project
- Core concept demos:
  - Basic methods and validation (`BasicsDemo`)
  - OOP with inheritance (`Person`, `Student`)
  - Collections and duplicate removal (`CollectionsDemo`)
- Unit tests with JUnit 5

## Project structure

```text
src/main/java/com/superpersonopc/learning
src/test/java/com/superpersonopc/learning
```

## Requirements

- Java 17+
- Maven 3.9+

## Run tests

```bash
mvn test
```

## Run the demo app

```bash
mvn -q exec:java -Dexec.mainClass=com.superpersonopc.learning.App
```

If the `exec` goal is unavailable, run directly with compiled classes:

```bash
mvn -q -DskipTests compile
java -cp target/classes com.superpersonopc.learning.App
```
