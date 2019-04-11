# Kafka Consumer
A simple demonstration of Kafka messaging system.

## Description
A number consumer that consumes numbers from a [Kafka topic](https://github.com/quangnvien/kafkanumberprovider) and triple it. Running this with [kafkanumberdouble](https://github.com/quangnvien/kafkanumberdouble) to see how different Kafka consuming services work. Both services consume the same Kafka topic. 

## Usage
- Start Zookeeper
```bash
bin\zookeeper-server-start.sh config\zookeeper.properties
```
- Start Kafka Brokers
```bash
bin\kafka-server-start.sh config\server{i}.properties
```
- Start Kafka Consumer
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
