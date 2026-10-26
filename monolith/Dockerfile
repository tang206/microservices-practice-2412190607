FROM docker.1ms.run/maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM docker.1ms.run/eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/pet-hospital-tfy-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]