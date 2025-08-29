# my-app

## Build
mvn -B clean package

## Run locally (jar)
java -jar target/my-app-1.0.0.jar

## Run tests
mvn test

## Build Docker image
docker build -t my-app:latest .

## Push to AWS ECR (example)
./scripts/push-to-ecr.sh 1.0.0
