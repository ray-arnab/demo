FROM java:8
MAINTAINER Arnab Ray <ray.arnab.82@gmail.com>

ENV SB_WORKDIR    "/opt/spring-boot"
ENV SB_PORT  "8080"

VOLUME /tmp

RUN mkdir -p ${SB_WORKDIR}
WORKDIR ${SB_WORKDIR}

#ARG JAR_FILE
#ADD target/${JAR_FILE} ./demo-app.jar

ADD target/springboot-demo-0.0.1-SNAPSHOT.jar ./demo-app.jar

EXPOSE ${SB_PORT}
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./demo-app.jar"]