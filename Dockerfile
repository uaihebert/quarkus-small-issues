FROM fabric8/java-centos-openjdk11-jdk AS builder

WORKDIR /deployments

ADD mvnw pom.xml ./
ADD .mvn ./.mvn
RUN ./mvnw --batch-mode dependency:go-offline

ADD src ./src
RUN ./mvnw clean --batch-mode compile
RUN ./mvnw clean --batch-mode package

# The following is due to https://github.com/moby/moby/issues/34645 - we hit this in CI
USER root
RUN chown -R root: .
USER joboss

FROM fabric8/java-centos-openjdk11-jre AS app

WORKDIR /deployments

ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV AB_ENABLED=jmx_exporter

COPY --from=builder deployments/target/lib/* ./lib/
COPY --from=builder deployments/target/*-runner.jar ./app.jar

EXPOSE 8080

CMD [ "/deployments/run-java.sh" ]
