pipeline{
    agent any

    stages{
        stage('Build jars'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build image'){
            steps{
            sh "docker build -t learn-docker-selenium"
            }
        }

        stage('Push docker image'){
            steps{
            sh "docker push learn-docker-selenium"
            }
        }
    }
}