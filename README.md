# Kafka Consumer
A simple demonstration of Kafka messaging system.

## Description
A number consumer that consumes numbers from a Kafka topic and triple it. Running this with [kafkanumberdouble](https://github.com/quangnvien/kafkanumberdouble) to see how different Kafka consuming services work. Both services consume the same Kafka topic. 

## Usage
```bash
java -jar kafkanumbertriple.jar 
```

## Technical Stack
- Java 12
- Gradle 5.3.1
- Spring Boot 2.1.4
- Spring Kafka 2.2.5 
- Apache Kafka 2.1.0

## License
[MIT](https://choosealicense.com/licenses/mit/)
