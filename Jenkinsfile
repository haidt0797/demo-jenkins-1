pipeline {
    agent any

    options {
        timestamps()
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
        DB_PORT = '3306'
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
                withCredentials([
                    string(
                        credentialsId: 'db-url',
                        variable: 'DB_URL'
                    )
                ]) {
                    sh '''
                      echo "🔍 Checking DB connectivity..."

                      echo "DB_URL=$DB_URL"

                      DB_HOST=$(echo "$DB_URL" | sed 's|jdbc:mysql://||' | cut -d: -f1)
                      DB_PORT=3306

                      if [ -z "$DB_HOST" ]; then
                        echo "❌ DB_HOST is empty"
                        exit 1
                      fi

                      echo "DB_HOST=$DB_HOST"
                      echo "DB_PORT=$DB_PORT"

                      RETRY=3
                      WAIT=5

                      for i in $(seq 1 $RETRY); do
                        echo "➡ Attempt $i/$RETRY"
                        if timeout 5 bash -c "</dev/tcp/$DB_HOST/$DB_PORT"; then
                          echo "✅ DB is reachable"
                          exit 0
                        fi
                        echo "⚠ DB not reachable, retry in ${WAIT}s..."
                        sleep $WAIT
                      done

                      echo "❌ DB Health Check FAILED after $RETRY attempts"
                      exit 1
                    '''
                }
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
