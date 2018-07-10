#!/usr/bin/env groovy

def call(){
    node {
        stage('Quality Assesment'){
            parallel {
                stage('Tests'){
                    withMaven(
                        maven: 'm3'
                    ){
                        sh "mvn test"
                    }
                }
                stage('Sonar'){
                    withMaven(
                        maven: 'm3'
                    ){
                        sh "mvn sonar:sonar"
                    }
                }
            }
        }
        stage('Package'){
            withMaven(
                maven: 'm3'
            ){
                sh "mvn -B package"
            }
        }
        stage('Push to Nexus'){
            withMaven(
                maven: 'm3'
            ){
                sh "mvn deploy"
            }
        }
    }
}