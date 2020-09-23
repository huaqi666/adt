#!/bin/bash
mvn clean compile package -Dmaven.test.skip=true

NAME=ali-docker-token
VER=1.2.2

docker build -t registry.cn-hangzhou.aliyuncs.com/protected/${NAME}:${VER} .

docker tag registry.cn-hangzhou.aliyuncs.com/protected/${NAME}:${VER} registry.cn-hangzhou.aliyuncs.com/protected/${NAME}:latest

docker images | grep registry.cn-hangzhou.aliyuncs.com/protected/${NAME} | awk '{print "docker push "$1":"$2}' | sh

docker run --rm registry.cn-hangzhou.aliyuncs.com/protected/${NAME}:latest

docker rmi -f latest ali-token:latest

docker tag registry.cn-hangzhou.aliyuncs.com/protected/${NAME}:latest ali-token:latest

docker images | grep registry.cn-hangzhou.aliyuncs.com/protected/${NAME} | awk '{print "docker rmi -f "$1":"$2}' | sh

#docker images | grep none | awk '{print $3 }' | xargs docker rmi