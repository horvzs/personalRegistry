#!/bin/bash
echo $PWD
cd $PWD
cd ../
echo $PWD
gradle clean build
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Deployment Successful"
else
echo "Deployment Failed"
fi