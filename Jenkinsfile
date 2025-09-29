pipeline {
  agent any
  environment {
    SPRING_BOOT_PORT = '8080'
    // AWS_REGION = 'ap-south-1'               // change region
    // AWS_ACCOUNT_ID = credentials('aws-account-id') // or use env var
    // ECR_REPO = 'my-app-repo'
    // IMAGE_TAG = "${env.BUILD_NUMBER}"
    // DOCKER_IMAGE = "${env.AWS_ACCOUNT_ID}.dkr.ecr.${env.AWS_REGION}.amazonaws.com/${ECR_REPO}:${IMAGE_TAG}"
    // MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
  }
  stages {
    stage('Checkout') {
        steps {
          timeout(time: 30, unit: 'SECONDS') {
            git url: 'https://github.com/abmaharana/ProjectX.git', branch: 'master', credentialsId: 'github-cred'
            // checkout scm 
          }
        }
    }
    stage('Build Jar') {
          steps {
            script {
              if (isUnix()) {
                sh 'mvn clean package -DskipTests'
              } else {
                bat 'mvn clean package -DskipTests'
              }
            }
          }
        }
    stage('Run Spring Boot App') {
          steps {
            script {
              if (isUnix()) {
                sh 'nohup mvn spring-boot:run & echo $! > target/app.pid'
                sh 'sleep 30'
                sh 'curl -f http://localhost:${SPRING_BOOT_PORT}/login || exit 1'
              } else {
                bat ''
                '
                start / B mvn spring - boot: run
                for / f "tokens=2" % % p in ('tasklist ^| findstr /B java.exe') do(
                    echo % % p > target\\ app.pid exit / b 0
                  )
                  echo No Java process found > target\\ app.pid ''
                '
                bat 'ping 127.0.0.1 -n 31 > nul'
                bat 'curl -f http://localhost:%SPRING_BOOT_PORT%/login || exit 1'
              }
            }
          }
        }
    stage('Run Tests') {
            steps {
              script {
                if (isUnix()) {
                  sh 'mvn test'
                } else {
                  bat 'mvn test'
                }
              }
            }
            post {
              always {
                // Publish Cucumber HTML report
                publishHTML([
                  reportDir: 'target/cucumber-reports/cucumber-html-reports',
                  reportFiles: 'overview-features.html',
                  reportName: 'Automation Report',
                  keepAll: true,
                  alwaysLinkToLastBuild: true,
                  allowMissing: true
                ])

                // Terminate application process if PID file exists
                script {
                  def pidFile = 'target/app.pid'
                  if (fileExists(pidFile)) {
                    def killCommand = isUnix() ? 'kill $(cat ${pidFile}) || true' : 'for /f "tokens=*" %p in (${pidFile}) do (taskkill /F /PID %p || exit 0)'
                    sh(script: killCommand, returnStatus: true)
                  }
                }
              }
              //   stage('Build Docker Image') {
              //     steps {
              //       sh "docker build -t ${ECR_REPO}:${IMAGE_TAG} ."
              //     }
              //   }

              //   stage('Login to ECR & Push') {
              //     steps {
              //       withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-creds']]) {
              //         sh '''
              //           aws --version
              //           aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
              //           aws ecr create-repository --repository-name ${ECR_REPO} --region ${AWS_REGION} || true
              //           docker tag ${ECR_REPO}:${IMAGE_TAG} ${DOCKER_IMAGE}
              //           docker push ${DOCKER_IMAGE}
              //         '''
              //       }
              //     }
              //   }

              //   stage('Deploy to ECS (optional)') {
              //     steps {
              //       echo 'Deploy step — use aws ecs update-service / terraform / kubectl depending on infra'
              //       // Example (replace with your ECS update commands / terraform)
              //       // sh "aws ecs update-service --cluster my-cluster --service my-service --force-new-deployment --region ${AWS_REGION}"
              //     }
              //   }
              // }

              // post {
              //   always {
              //     echo "Clean workspace"
              //     cleanWs()
              //   }
              // }
            }
        }
}
