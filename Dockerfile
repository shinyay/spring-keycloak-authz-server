FROM maven:3.8.1-openjdk-11-slim as java-build
WORKDIR /app
COPY pom.xml /app/
RUN mvn -e -B dependency:resolve
COPY src /app/src
RUN mvn clean -e -B package -DskipTests

FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=java-build /app/target/oauth-authorization-server-*.jar /app/app.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-jar" "app.jar"