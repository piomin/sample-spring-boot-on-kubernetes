pipeline{
    agent any
    environment {
        imagename = "saaspeprivatehub.azurecr.io/jenkins"
        registryCredential = 'Acr_Id'
        dockerImage = ''
        registryUrl = 'saaspeprivatehub.azurecr.io'
        
    }
    stages{
        stage('git fetch'){
            steps{
                git 'https://github.com/vinaykumar15061992/Jenkins-with-Github-Actions.git'
            }
        }
        stage('Building image') {
            steps{
                script {
                    dockerImage = docker.build imagename
                }
            }
        }
        stage('Deploy Image') {
            steps{
                script {
                    docker.withRegistry( "http://${registryUrl}", registryCredential )  {
                    dockerImage.push("$BUILD_NUMBER")
                    dockerImage.push('latest')
                    }
                }
            }
        }
        stage('deploying app to kubernetes'){
            steps{
                sh 'kubectl apply -f deployment.yml'
            }
        }
    }
} 
