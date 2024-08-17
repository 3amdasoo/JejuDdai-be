# Build stage
FROM krmp-d2hub-idock.9rum.cc/goorm/gradle:7.5.1-jdk17 AS build

WORKDIR /app
# 각자의 프로젝트 파일에 맞게 변경하십셔
COPY ./build/libs/jejuddai-0.0.1-SNAPSHOT.jar /app/jejuddai.jar
ENV TZ=Asia/Seoul

CMD ["java", "-jar", "jejuddai.jar"]