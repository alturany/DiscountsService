pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Hello World!'
      }
    }

    stage('') {
      steps {
        emailext(subject: 'Hi', body: 'Hello World Email')
      }
    }

  }
}