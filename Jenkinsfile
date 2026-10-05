pipeline {

    agent any

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/nikitababashev/api-autotests'
            }
        }


        stage('API Tests') {
            steps {
                sh './gradlew clean test'
            }
        }

    }

}