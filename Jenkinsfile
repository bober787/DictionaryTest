pipeline {
  agent any

  tools {
    jdk 'jdk11'
  }

  environment {
    API_TOKEN = credentials('api-token')
    SELENOID_URL = 'http://selenoid:4444/wd/hub'
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/ваш_логин/DictionaryTest.git'
      }
    }

    stage('Build') {
      steps {
        sh './gradlew build -x test'
      }
    }

    stage('Run Tests') {
      steps {
        sh '''
          export API_TOKEN=$API_TOKEN
          export SELENOID_URL=$SELENOID_URL
          ./gradlew test
        '''
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'build/reports/**/*', allowEmptyArchive: true
      junit 'build/test-results/test/*.xml'
    }
  }
}
