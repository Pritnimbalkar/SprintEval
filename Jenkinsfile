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

        stage('Generate Allure Report') {

            steps {

                dir('Sprint/automation-framework') {

                    bat 'D:\\allure2\\bin\\allure.bat generate allure-results --clean -o Defects'
                }
            }
        }

        stage('Open Allure Report') {

            steps {

                dir('Sprint/automation-framework') {

                    bat 'D:\\allure2\\bin\\allure.bat open Defects'
                }
            }
        }
    }
}
