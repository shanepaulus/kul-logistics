pipeline {
  agent any

  stages {
    stage("Verify") {
      steps {
        withGradle() {
          sh "gradle -v"
        }
      }
    }
  }
}