pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo "Code checked out from Git"
                // git branch: 'main', url: 'your-repo-url'
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

        stage('Deploy to DEV') {
            when {
                branch 'develop'
            }
            steps {
                echo "Deploying to DEV environment..."
                sh 'java -jar -Dspring.profiles.active=dev target/profile-app-1.0.0.jar &'
                echo "DEV deployment completed - App running on port 8081"
            }
        }

        stage('Deploy to TEST') {
            when {
                branch 'release/*'
            }
            steps {
                echo "Deploying to TEST environment..."
                sh 'java -jar -Dspring.profiles.active=test target/profile-app-1.0.0.jar &'
                echo "TEST deployment completed - App running on port 8082"
            }
        }

        stage('Deploy to PROD') {
            when {
                branch 'main'
            }
            steps {
                echo "Deploying to PRODUCTION environment..."
                sh 'java -jar -Dspring.profiles.active=prod target/profile-app-1.0.0.jar &'
                echo "PROD deployment completed - App running on port 8080"
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