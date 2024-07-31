pipeline {
    agent any
    
    tools {
        nodejs 'NodeJS' // Name of the NodeJS installation in Jenkins
    }
     environment {
        registry = "yousseflogtari/rne" // Docker repository name
        dockerImageTag = "v1" // Tag for Docker image
    }

    stages {
        stage('Checkout Angular App') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                git branch: 'RNEfrontAngular',
                    url: 'https://github.com/zaineb-bouallegui/LCP2.git', // Replace with your repository URL
                    credentialsId: 'Token-jenkins' // Use the ID of your GitHub credential
            }
        }
        
        stage('Install Angular CLI') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                sh 'npm install -g @angular/cli@16'
            }
        }

        stage('Build Angular App') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                sh 'npm install'
                sh 'ng build'
            }
        }
        
        stage('Dockerize Angular App') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                script {
                    // Build Docker image
                    dockerImage = docker.build("${registry}:${dockerImageTag}", "-f dockerfile .")
                }
            }
        }

        stage('Push Docker Image') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                script {
                    // Push Docker image to your Docker repository
                    docker.withRegistry('https://index.docker.io/v1/', 'jenkins-docker') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo 'Cleaning up...'
        }
    }
}
