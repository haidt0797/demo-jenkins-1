pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timeout(time: 15, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    tools{
        jdk 'java-8'
    }

        stage('Config UAT') {
            when {
                beforeAgent true
                anyOf {
                    branch 'master'
                    branch 'uat'
                    branch 'uat-*'
                }
            }
            steps {
                sh 'cp -rf env/uat/* src/main/resources/config'
            }
        }

        stage('Config SIT') {
            when {
                beforeAgent true
                anyOf {
                    branch 'sit'
                    branch 'sit-*'
                }
            }
            steps {
                sh 'cp -rf env/sit/* src/main/resources/config'
            }
        }

        stage('Config STAGING') {
            when {
                beforeAgent true
                allOf {
                    branch 'v*.*.*'
                     expression{env.TAG_NAME == null}
                }
            }
            steps {
                sh 'cp -rf env/staging/* src/main/resources/config'
            }
        }

        stage('Config LIVE') {
            when {
                beforeAgent true
                allOf {
                    branch 'v*.*.*'
                    tag 'v*.*.*'
                }
            }
            steps {
                sh 'cp -rf env/live/* src/main/resources/config'
            }
        }

        stage('Config DEVELOP') {
            when {
                beforeAgent true
                anyOf {
                    branch 'dev'
                    branch 'develop'
                    branch 'feature/*'
                    branch 'hotfix/*'
                    branch 'release/*'
                }
            }
            steps {
                sh 'cp -rf env/develop/* src/main/resources/config'
            }
        }

        stage('Compile') {
            steps {
                sh './mvnw compile'
            }
        }

        stage('Package') {
            steps {
                sh './mvnw -DskipTests package'
            }
        }

    }

     post {
        always {
            archiveArtifacts artifacts: 'target/*.jar, target/*.war', fingerprint: true, allowEmptyArchive: true, onlyIfSuccessful: true
            slackSend failOnError: false, color: "${currentBuild.currentResult.equals('SUCCESS') ? 'good': 'danger'}", message: "Job ${env.JOB_NAME} - Build #${env.BUILD_NUMBER} ${currentBuild.currentResult} after ${currentBuild.durationString.minus(' and counting')} (<${env.BUILD_URL}|Open>)"
        }
    }
}

@NonCPS
def commitInfo(commit) {
    return commit != null ? "`${commit.commitId.take(7)}`  *${commit.msg}*  _by ${commit.author}_ \n" : ""
}

@NonCPS
String changeLog(){
    String msg = ""
    def changeLogSets = currentBuild.changeSets

    for (int i = 0; i < changeLogSets.size(); i++) {
            def entries = changeLogSets[i].items
            for (int j = 0; j < entries.length; j++) {
                def entry = entries[j]
                msg = msg + "${commitInfo(entry)}"
            }
        }
    return msg
}