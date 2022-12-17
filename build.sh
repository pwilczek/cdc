#!/bin/bash

./gradlew clean build
docker build --tag cdc .