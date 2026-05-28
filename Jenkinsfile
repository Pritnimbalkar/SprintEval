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

                dir('Sprint/automation-framework') {

                    bat 'mvn clean test'
                }
            }
        }
    }

    post {

        always {

            dir('Sprint/automation-framework') {

                allure(
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'allure-results']]
                )
            }
        }
    }
}
