pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "your-docker-registry/your-app:latest"
        GIT_CREDENTIALS = 'your-git-credentials'
        GIT_REPO_URL = 'https://github.com/Aminur01/CWA'
        GIT_BRANCH = 'master'
    }

    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: GIT_BRANCH]], extensions: [], userRemoteConfigs: [[url: GIT_REPO_URL]])
                bat 'mvn clean install'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
