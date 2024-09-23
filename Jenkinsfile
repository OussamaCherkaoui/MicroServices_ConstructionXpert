pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
        SONARQUBE_TOKEN = 'squ_03da3b53ba5db7f261b4e28228763bd29a5af402'
        SONARQUBE_SERVER = 'http://localhost:9000'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/OussamaCherkaoui/MicroServices_ConstructionXpert.git'
            }
        }

        stage('Build & Test Microservices') {
            parallel {
                stage('Build & Test Authentification-Service') {
                    steps {
                        dir('Authentification-Service) {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test Project') {
                    steps {
                        dir('Project') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test Task') {
                    steps {
                        dir('Task') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test Ressource') {
                    steps {
                        dir('Ressource') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Package Api-Gateway') {
                    steps {
                        dir('Api-Gateway') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test Eureka') {
                    steps {
                        dir('Eureka') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQube'


                    dir('Authentification-Service') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Authentification-Service -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('Project') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Project -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('Task') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Task -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('Ressource') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Ressource -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('Api-Gateway') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Api-Gateway -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('Eureka') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Eureka -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }

        stage('Build Docker Images & Push') {
            parallel {
                stage('Build Docker & Push for Authentification-Service') {
                    steps {
                        dir('Authentification-Service') {
                            script {
                                def dockerImage = docker.build("oussamacherkaoui/authentification-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for Project') {
                    steps {
                        dir('Project') {
                            script {
                                def dockerImage = docker.build("oussamacherkaoui/project-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for Task') {
                    steps {
                        dir('Task') {
                            script {
                                def dockerImage = docker.build("oussamacherkaoui/task-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for Ressource') {
                    steps {
                        dir('Ressource') {
                            script {
                                def dockerImage = docker.build("oussamacherkaoui/ressource-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for Api-Gateway') {
                    steps {
                        dir('Api-Gateway') {
                            script {
                                def dockerImage = docker.build("oussamacherkaoui/api-gateway:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for Eureka') {
                    steps {
                        dir('Eureka') {
                            script {
                                def dockerImage = docker.build("oussamacherkaoui/eureka-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                bat 'docker-compose up'
            }
        }
    }
}
