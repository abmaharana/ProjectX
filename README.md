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

## Build Docker container
docker-compose up --build -d
docker-compose up --build --force-recreate -d


## list containers
docker ps -a


## Run Tests Manually in docker VM: 
entrypoint >
  sh -c "tail -f /dev/null"
  
docker exec projectx-tests sh -c "mvn clean test -Dexecution=docker -DbaseUrl=http://app:8080"


## Run Tests Automatically in docker VM (through bash in docker-compose file)
entrypoint >
  sh -c "sleep 30 && mvn clean test -Dexecution=docker -DbaseUrl=http://app:8080"


## Close docker container and images:
docker-compose down
docker rm <image_id>


## To enter inside the docker container
docker exec -it <container_id> /bin/bash


## Push to AWS ECR (example)
./scripts/push-to-ecr.sh 1.0.0
