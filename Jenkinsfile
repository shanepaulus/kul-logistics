pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "gradle clean build -x test"
            }
        }

        stage('Test') {
            steps {
                sh "gradle test"
            }
        }

        stage('Deploy') {
            steps {
                //sh "gradle clean build -x"
                echo "Deploying...."
            }
        }
    }
}