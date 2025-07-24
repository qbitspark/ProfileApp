pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo "Code checked out from Git"
            }
        }

        stage('Build') {
            steps {
                echo "Building the application..."
                sh './mvnw clean compile'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
                sh './mvnw test'
            }
        }

        stage('Package') {
            steps {
                echo "Packaging the application..."
                sh './mvnw package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Determine environment based on branch
                    def branch = env.GIT_BRANCH
                    if (branch.contains('develop')) {
                        echo "Deploying to DEV environment..."
                        sh 'java -jar -Dspring.profiles.active=dev target/profile-app-1.0.0.jar &'
                        echo "DEV deployment completed - App running on port 8091"
                    } else if (branch.contains('release/')) {
                        echo "Deploying to TEST environment..."
                        sh 'java -jar -Dspring.profiles.active=test target/profile-app-1.0.0.jar &'
                        echo "TEST deployment completed - App running on port 8092"
                    } else if (branch.contains('master')) {
                        echo "Deploying to PRODUCTION environment..."
                        sh 'java -jar -Dspring.profiles.active=prod target/profile-app-1.0.0.jar &'
                        echo "PROD deployment completed - App running on port 8090"
                    } else {
                        echo "No deployment configured for branch: ${branch}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed!"
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            echo "Build succeeded! ✅"
        }
        failure {
            echo "Build failed! ❌"
        }
    }
}