FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
