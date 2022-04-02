pipeline {
    agent{ label 'Jenkins-Slave-Ubuntu' }

    environment {
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
        version="1.0.0"
    }

    stages {
        stage('GIT Clone') { 
            steps {
                git branch: 'main', url: 'https://github.com/KenSingson/node-sample-app.git'
            }
        }

        stage('BUILD') {
            steps {
                sh 'docker build -t kensingson/nodeapp_test:$version .'
            }
        }

        stage('Login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push to docker hub') {
            steps {
                sh 'docker push kensingson/nodeapp_test:$version'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}