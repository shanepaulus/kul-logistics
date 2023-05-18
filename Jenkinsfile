pipeline {
  agent any

  stages {
    stage("Verify") {
      def gradleHome = tool name: 'gradle-7.6', type: 'gradle'

      steps {
        withGradle() {
          sh "${gradleHome}/bin/gradle -v"
        }
      }
    }
  }
}