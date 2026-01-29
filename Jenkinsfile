pipeline {
    agent any

    options {
        timestamps()
        ansiColor('xterm')
        timeout(time: 20, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '30'))
    }

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        APP_NAME = 'demo-jenkins-1'
        SPRING_PROFILES_ACTIVE = 'ci'
    }

    stages {

        stage('Checkout Source') {
            steps {
                checkout scm
            }
        }

        stage('Environment Validation') {
            steps {
                sh '''
                  echo "APP_NAME=$APP_NAME"
                  echo "PROFILE=$SPRING_PROFILES_ACTIVE"
                  java -version
                  mvn -version
                '''
            }
        }

        stage('Load Credentials') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'db-credential',
                        usernameVariable: 'DB_USER',
                        passwordVariable: 'DB_PASS'
                    ),
                    string(
                        credentialsId: 'db-url',
                        variable: 'DB_URL'
                    )
                ]) {
                    sh '''
                      echo "DB_URL=$DB_URL"
                    '''
                }
            }
        }

        stage('DB Health Check') {
            steps {
                sh '''
                  echo "Checking DB connectivity..."
                  nc -zv $(echo $DB_URL | sed 's|jdbc:mysql://||' | cut -d: -f1) 3306
                '''
            }
        }

        stage('Build') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'db-credential',
                        usernameVariable: 'DB_USER',
                        passwordVariable: 'DB_PASS'
                    ),
                    string(
                        credentialsId: 'db-url',
                        variable: 'DB_URL'
                    )
                ]) {
                    sh '''
                      mvn clean package \
                        -DskipTests=false \
                        -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE
                    '''
                }
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ BUILD SUCCESS'
        }
        failure {
            echo '❌ BUILD FAILED'
        }
        always {
            cleanWs()
        }
    }
}
