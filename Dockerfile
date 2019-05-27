FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /tripler/lib
COPY ${DEPENDENCY}/META-INF /tripler/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /tripler
ENTRYPOINT ["java","-cp","tripler:tripler/lib/*","vn.quang.KafkaMain"]