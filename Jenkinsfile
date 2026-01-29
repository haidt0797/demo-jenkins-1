pipeline {
    agent any

    options {
        timeout(time: 20, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
        skipStagesAfterUnstable()
    }

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        APP_NAME = 'demo-jenkins-1'
        SPRING_PROFILES_ACTIVE = 'local'   // ⭐ RẤT QUAN TRỌNG
        DB_URL = 'jdbc:mysql://host.docker.internal:3306/demo'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Show Environment') {
            steps {
                sh '''
                echo "APP_NAME=$APP_NAME"
                echo "SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE"
                echo "DB_URL=$DB_URL"
                java -version
                mvn -version
                '''
            }
        }

        stage('Debug DB') {
            steps {
                sh '''
                  echo "DB_URL=$DB_URL"
                  echo "DB_USER=$DB_USER"
                  nc -zv host.docker.internal 3306 || true
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
                    )
                ]) {
                    sh '''
                    mvn clean package -DskipTests \
                      -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE \
                      -Dspring.datasource.url=$DB_URL \
                      -Dspring.datasource.username=$DB_USER \
                      -Dspring.datasource.password=$DB_PASS
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo '✅ BUILD SUCCESS'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo '❌ BUILD FAILED'
        }
        always {
            cleanWs()
        }
    }
}
