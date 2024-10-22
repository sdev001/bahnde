pipeline {
    agent any
    stages {
       stage('git checkout')
       {
           steps {
                script {
                    git branch: 'main',
                    url: 'https://github.com/sdev001/bahnde.git'
                }
           }
        }
        stage ('run tests')
        {
            steps {
				timeout(time: 15, unit: 'MINUTES') {
                        sh 'mvn clean test -X'
                }
            }
        }
        stage('export results'){
            steps{
                step([$class: 'XrayImportBuilder', endpoint:'CUCUMBER',endpointName:'/cucumber',
                importFilePath:'target/cucumber-reports/Cucumber.json',testExecKey:'DBS-15',testPlanKey:'DBS-14', importToSameExecution: 'true',
                serverInstance:'a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6', projectKey :'DBS'])
            }
        }
    }
	post{
	    always {
	           cucumber '**/Cucumber.json'
	           allure includeProperties:
	           false,
	           results: [['target/allure-results']]
	    }
	}

}