#!/bin/bash

./gradlew clean build
docker build -f ./donations/Dockerfile --tag donations .
docker build -f ./balance/Dockerfile --tag balance .
