#!/bin/bash

./gradlew clean build
docker build -f ./debezium/Dockerfile --tag debezium .
docker build -f ./donations/Dockerfile --tag donations .