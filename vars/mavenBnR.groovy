#!/usr/bin/env groovy

def call(String mavenName = 'm3'){
    node {
        stage('Quality Assesment'){
            parallel {
                stage('Tests'){
                    withMaven(
                        maven: "${mavenName}"
                    ){
                        sh "mvn test"
                    }
                }
                stage('Sonar'){
                    withMaven(
                        maven: "${mavenName}"
                    ){
                        sh "mvn sonar:sonar"
                    }
                }
            }
        }
        stage('Package'){
            withMaven(
                maven: "${mavenName}"
            ){
                sh "mvn -B package"
            }
        }
        stage('Push to Nexus'){
            withMaven(
                maven: "${mavenName}"
            ){
                sh "mvn deploy"
            }
        }
    }
}