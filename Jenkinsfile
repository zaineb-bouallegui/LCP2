pipeline {
    agent any
    
    tools {
        nodejs 'NodeJS' // Name of the NodeJS installation in Jenkins
        maven 'Maven' // Name of the Maven installation in Jenkins
    }

    stages {
        // Stage for building the Angular app
        stage('Checkout Angular App') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                git branch: 'RNEfrontAngular',
                    url: 'https://github.com/zaineb-bouallegui/LCP2.git',
                    credentialsId: 'Token-jenkins'
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
        
        // Stage for building the Spring Boot config-server
        stage('Checkout Backend Config Server') {
            when {
                branch 'RNEbackendSpring'
            }
            steps {
                git branch: 'RNEbackendSpring',
                    url: 'https://github.com/zaineb-bouallegui/LCP2.git',
                    credentialsId: 'Token-jenkins'
            }
        }
        
        stage('Build Config Server') {
            when {
                branch 'RNEbackendSpring'
            }
            steps {
                dir('config-server') {
                    sh 'mvn clean install'
                }
            }
        }
    }
    
    post {
        success {
            echo 'Build completed successfully. Cleaning up...'
            cleanWs() // This will clean the workspace
        }
        failure {
            echo 'Build failed. Please check the logs for more details.'
        }
       
    }
}
