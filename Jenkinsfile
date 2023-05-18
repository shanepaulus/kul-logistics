pipeline {
  agent any

  tools {
    gradle 'gradle-7.6'
  }

  stages {
    stage("Verify") {
      steps {
        withGradle() {
          sh "gradle -v"

      }
    }
  }
}