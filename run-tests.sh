#!/bin/sh

echo "Waiting for Selenium Hub..."
until curl -s --fail http://selenium-hub:4444/status | grep '"ready":true'; do
  echo "Selenium Hub not ready, retrying in 5 seconds..."
  sleep 5
done
echo "Selenium Hub is ready!"

echo "Waiting for app..."
until curl -s --fail http://app:8080; do
  echo "App not ready, retrying in 5 seconds..."
  sleep 5
done
echo "App is ready!"

echo "Running tests..."
mvn clean test -Dexecution=docker -DbaseUrl=http://app:8080