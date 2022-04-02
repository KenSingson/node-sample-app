pipeline {
    agent{ label 'Jenkins-Slave-Ubuntu' }

    environment {
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    }

    stages {
        stage('GIT Clone') { 
            steps {
                git 'https://github.com/KenSingson/node-sample-app.git'
            }
        }

        stage('BUILD') {
            steps {
                sh 'docker build -t kensingson/nodeapp_test:latest .'
            }
        }

        stage('Login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push to docker hub') {
            steps {
                sh 'docker push thetips4you/nodeapp_test:latest'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}