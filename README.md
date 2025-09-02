# my-app

## Build
mvn -B clean package

## Run locally (jar)
java -jar target/my-app-1.0.0.jar

## Run all tests
mvn test 

## Run locally
mvn clean test -Dexecution=local -DbaseUrl=http://localhost:8080

## Run with report generation
mvn clean verify

## Build Docker image
docker build -t my-app:latest .

## Push to AWS ECR (example)
./scripts/push-to-ecr.sh 1.0.0
