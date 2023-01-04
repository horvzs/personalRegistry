#!/bin/bash
echo $PWD
cd $PWD
./gradlew build
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Deployment Successful"
else
echo "Deployment Failed"
fi