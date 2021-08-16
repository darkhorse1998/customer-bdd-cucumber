pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {

                sh "cd customer-supermarket-app; mvn clean compile"
            }
        }
       
		stage('Junit5 Test') { 
            steps {

                sh "cd customer-supermarket-app; mvn test"
            }
        }
        

		stage('Jacoco Coverage Report') {
        	steps{
            		jacoco()
			}
		}
        
        stage('SonarQube'){
			steps {
				sh "cd customer-supermarket-app; mvn sonar:sonar -Dsonar.host.url=http://18.210.22.117:9000 -Dsonar.login=91fa43fe686b5ab0d9dfe42cd13cd5f90dc8f7a3"
		         
		     }
   		}
   		
        stage('Maven Build') { 
            steps {
                sh "cd customer-supermarket-app; mvn package"
            }
        }


        stage('Build Docker image'){
            steps {
              
                sh 'cd customer-supermarket-app; docker build -t  hkalita1998/customer_management_service:1.0 .'
            }
        }
        

        stage('Docker Login'){
            
            steps {
                 withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "cd customer-supermarket-app; docker login -u hkalita1998 -p ${Dockerpwd}"
                }
            }                
        }

        stage('Docker Push'){
            steps {
                sh 'cd customer-supermarket-app; docker push hkalita1998/customer_management_service:1.0'
            }
        }
        
        stage('Archiving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
        stage('Generate HTML report') {
            steps{
        			cucumber buildStatus: 'UNSTABLE',
                		reportTitle: 'My Cucumber Report',
                		fileIncludePattern: '**/*.json',
               			trendsLimit: 10,
                		classifications: [
                    		[
                        		'key': 'Browser',
                        		'value': 'Firefox'
                    		]
                		]
                  }
			}
    }
}

