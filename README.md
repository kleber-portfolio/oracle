# Oracle App (Java AWS Lambda)

Oracle is an AWS Lambda function written in Java 17 using Spring Boot 3.4.4. It listens to messages from an SQS queue to determine whether a person is "chosen" or not. If chosen, a comforting message is generated and stored in DynamoDB.

---

<p align="center">
  <img src="image/application.png" alt="Application Architecture" width="600"/>
</p>

## Overview

- Deployment: AWS Lambda
- Trigger: Amazon SQS (ChosenPerson queue)
- Language: Java 17
- Framework: Spring Boot 3.4.4
- Persistence: Amazon DynamoDB
- Architecture: Spring Cloud Function (functional style)

---

## Architecture

```plaintext
SQS (chosen-person.fifo)
        │
        ▼
   AWS Lambda (Oracle Function)
        │
        ├──> Checks if person is "chosen"
        │       ├── If yes: save to DynamoDB
        │       └── If not: send message to producer
        │
        ▼
DynamoDB (person-dev table)