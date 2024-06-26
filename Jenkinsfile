pipeline {
    agent any
    
    tools {
        nodejs 'NodeJS' // Name of the NodeJS installation in Jenkins
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
        
        stage('Build Angular App') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                sh 'npm install'
                sh 'ng build'
            }
        }
    }
    
    post {
        success {
            echo 'Cleaning up...'
            
        }
    }
}
