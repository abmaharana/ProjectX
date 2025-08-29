pipeline {
  agent any
  environment {
    AWS_REGION = 'ap-south-1'               // change region
    AWS_ACCOUNT_ID = credentials('aws-account-id') // or use env var
    ECR_REPO = 'my-app-repo'
    IMAGE_TAG = "${env.BUILD_NUMBER}"
    DOCKER_IMAGE = "${env.AWS_ACCOUNT_ID}.dkr.ecr.${env.AWS_REGION}.amazonaws.com/${ECR_REPO}:${IMAGE_TAG}"
    MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build Jar') {
      steps {
        sh "mvn -B clean package -DskipTests"
      }
    }

    stage('Run Tests') {
      steps {
        // run cucumber + testng; results go to target/allure-results
        sh "mvn -B test"
      }
      post {
        always {
          // archive allure results
          archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
          junit 'target/surefire-reports/**/*.xml' // if you have junit xmls
        }
      }
    }

    stage('Allure Report Publish') {
      steps {
        // requires allure commandline installed on agent OR use plugin
        sh "mvn allure:report || true"
        // optionally archive generated report
        archiveArtifacts artifacts: 'target/site/allure-maven-plugin/**', allowEmptyArchive: true
      }
    }

    stage('Build Docker Image') {
      steps {
        sh "docker build -t ${ECR_REPO}:${IMAGE_TAG} ."
      }
    }

    stage('Login to ECR & Push') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-creds']]) {
          sh '''
            aws --version
            aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
            aws ecr create-repository --repository-name ${ECR_REPO} --region ${AWS_REGION} || true
            docker tag ${ECR_REPO}:${IMAGE_TAG} ${DOCKER_IMAGE}
            docker push ${DOCKER_IMAGE}
          '''
        }
      }
    }

    stage('Deploy to ECS (optional)') {
      steps {
        echo 'Deploy step — use aws ecs update-service / terraform / kubectl depending on infra'
        // Example (replace with your ECS update commands / terraform)
        // sh "aws ecs update-service --cluster my-cluster --service my-service --force-new-deployment --region ${AWS_REGION}"
      }
    }
  }

  post {
    always {
      echo "Clean workspace"
      cleanWs()
    }
  }
}
