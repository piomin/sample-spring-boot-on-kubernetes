FROM maven:3.8.6-openjdk-11 as build
WORKDIR /workspace/app

COPY pom.xml .

RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline

COPY . .
RUN mvn clean package -Dmaven.test.skip=true


FROM openjdk:21-buster
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build /workspace/app/target/sample-spring-boot-on-kubernetes-1.3-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]