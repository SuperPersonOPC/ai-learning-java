# ai-learning-java

It is for AI learning in Java language.

## Spring Boot + Spring AI module

This repository now includes a Maven-based Spring Boot module integrated with Spring AI.

### Stack

- Java 21
- Spring Boot 3.3.5
- Spring AI 1.0.0
- OpenAI chat model starter

### Configure API key

Set your API key as an environment variable:

```bash
export OPENAI_API_KEY="your_api_key_here"
```

### Run

```bash
./mvnw spring-boot:run
```

or with a local Maven install:

```bash
mvn spring-boot:run
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
