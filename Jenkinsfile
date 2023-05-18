pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "gradle clean build -x"
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
            }
        }
    }
}