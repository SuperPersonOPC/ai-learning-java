# ai-learning-java

It is for AI learning in Java language.

## Project structure

This repository is split into three Maven modules:

- `java-learning-module`: Core Java examples (basics, collections, OOP) and unit tests.
- `spring-ai-module`: Spring Boot app integrated with Spring AI for chat completions.
- `rag-demo-module`: Spring Boot app demonstrating a simple Retrieval-Augmented Generation (RAG) flow.

### Stack

- Java 21
- Spring Boot 3.3.5
- Spring AI 1.0.0
- OpenAI chat model starter

## Build and test all modules

```bash
mvn clean verify
```

## Run the Java learning demo

```bash
mvn -pl java-learning-module exec:java -Dexec.mainClass=com.superpersonopc.learning.App
```

### Configure API key

Set your API key as an environment variable:

```bash
export OPENAI_API_KEY="your_api_key_here"
```

### Run

```bash
mvn -pl spring-ai-module spring-boot:run
```

### API

Endpoint:

```text
POST /api/v1/chat
Content-Type: application/json
```

Body:

```json
{
  "message": "Explain Spring AI in one paragraph"
}
```

Example with curl:

```bash
curl -X POST "http://localhost:8080/api/v1/chat" \
  -H "Content-Type: application/json" \
  -d '{"message":"What is Spring AI?"}'
```

## Run the RAG demo module

```bash
mvn -pl rag-demo-module spring-boot:run
```

### RAG API

Endpoint:

```text
POST /api/v1/rag
Content-Type: application/json
```

Body:

```json
{
  "question": "How does RAG reduce hallucinations?"
}
```

Example with curl:

```bash
curl -X POST "http://localhost:8081/api/v1/rag" \
  -H "Content-Type: application/json" \
  -d '{"question":"Explain the basic RAG workflow"}'
```
