#!/usr/bin/env groovy

def call(String pythonVitualEnv = 'py3'){
    stage('Setup'){
        withPythonEnv('demo'){
            sh "pip install -r requirements.txt"
        }
    }
    stage('Tests'){
        withPythonEnv('demo'){
            sh "python -m unittest "
        }
    }
    stage('Sonar'){
        withPythonEnv('demo'){
            withSonarQubeEnv {
                sh "sonar-scanner -Dsonar.projectKey=python -Dsonar.sources=."
            }
        }
    }
    stage('Package'){
        withPythonEnv('demo'){
            sh "conda build python-demo" //TODO: Find correct command
        }
    }
    stage('Push to Nexus'){
        sh "curl to nexus" //TODO: Find correct command
    }
}