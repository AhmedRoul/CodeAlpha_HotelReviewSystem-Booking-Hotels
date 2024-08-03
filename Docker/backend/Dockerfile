# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /build
COPY ../pom.xml .
RUN mvn dependency:go-offline
COPY ../src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/Hotel-Review-System-*.jar /app/

EXPOSE 8088

ENV DB_URL=jdbc:postgresql://database-hotel-booking-hotel-review-system.j.aivencloud.com:27030/defaultdb?ssl=require
ENV MAILDEV_URL=localhost
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD ["java", "-jar", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Dspring.datasource.url=${DB_URL}", "Hotel-Review-System-${JAR_VERSION}.jar"]
