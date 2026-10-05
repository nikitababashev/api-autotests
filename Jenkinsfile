pipeline {

    agent any


    environment {

        TEST_EMAIL = credentials('TEST_EMAIL')
        TEST_API_KEY = credentials('TEST_API_KEY')

    }


    stages {


        stage('API Tests') {

            steps {

                bat 'gradlew.bat clean test'

            }

        }

    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'build/test-results/test/*.xml'
        }
    }

}