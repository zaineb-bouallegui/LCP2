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
        stage('Docker') {
            when {
                branch 'RNEfrontAngular'
            }
            steps {
                script {
                    if (!fileExists('dist')) {
                        error "Build failed due to missing target directory"
                    } else {
                        sh 'ls -l dist'
                        
                        withDockerRegistry(credentialsId: 'jenkins-dist', toolName: 'Docker') {
                            sh 'docker build -t yousseflogtari/youssef:firstTag .'
                            sh 'docker push yousseflogtari/youssef:firstTag'
                        }
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
