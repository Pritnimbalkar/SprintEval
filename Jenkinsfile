pipeline {

    agent any

    stages {

        stage('Clean Workspace') {

            steps {

                deleteDir()
            }
        }

        stage('Checkout') {

            steps {

                git branch: 'main',
                    url: 'https://github.com/Pritnimbalkar/SprintEval.git'
            }
        }

        stage('Build & Test') {

            steps {

                bat 'mvn clean test'
            }
        }

        stage('Allure Report') {

            steps {

                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }
    }
}
