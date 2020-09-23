FROM openjdk:alpine

MAINTAINER cliod<qi2415@qq.com>

ADD target/ali-docker-token.jar /root/app.jar

ENV TimeZone=Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/root/app.jar"]
