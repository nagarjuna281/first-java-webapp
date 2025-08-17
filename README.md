# 🚀 Java Web Application Deployment with Jenkins & Tomcat on EC2

This project demonstrates how to deploy a simple Java web application using Jenkins and Apache Tomcat, hosted on two separate EC2 instances. The CI/CD pipeline automates code retrieval, build, and deployment using Maven and Jenkins.

---

## 🧱 Infrastructure Overview

| Component        | Purpose                  |
|------------------|---------------------------|
| EC2 Instance #1  | Jenkins Server (CI/CD)    |
| EC2 Instance #2  | Apache Tomcat Server      |

---

## 📦 Application Repository

Source code is hosted on GitHub:  
🔗 [first-java-webapp](https://github.com/nagarjuna281/first-java-webapp.git)

---

## 🔧 Setup Instructions

### 1. Provision EC2 Instances
- Launch two Ubuntu EC2 instances.
- Assign Elastic IPs.
- Open ports:
  - Jenkins: `8080`
  - Tomcat: `8080`
  - SSH: `22`

---

### 2. Jenkins Setup (EC2 #1)
- Access Jenkins at `http://<JENKINS_EC2_IP>:8080`
- Install required plugins:
  - Git
  - Maven Integration
  - Deploy to Container

- Configure Maven under **Manage Jenkins → Global Tool Configuration** with name: `mymaven`
- Add Jenkins credentials:
  - ID: `tomcat`
  - Username: `tomcat`
  - Password: `admin@123`

---

### 3. Tomcat Setup (EC2 #2)

#### 🔐 Configure Remote Deployment Access

- Access Tomcat at `http://<TOMCAT_EC2_IP>:8080`

---

## 🧪 Jenkins Pipeline Configuration

### 🔧 Jenkinsfile

```groovy
pipeline {
    agent any
    tools {
        maven "mymaven"
    }
    stages {
        stage('Code from GitHub') {
            steps {
                git branch: 'main', url: 'https://github.com/nagarjuna281/first-java-webapp.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                deploy adapters: [
                    tomcat9(
                        alternativeDeploymentContext: '', 
                        credentialsId: 'tomcat', 
                        path: '', 
                        url: 'http://<TOMCAT_EC2_IP>:8080'
                    )
                ], 
                contextPath: 'myapp', 
                war: 'target/*.war'
            }
        }
    }
}
```
---

## 🛠️ Creating the Jenkins Pipeline Job

1. Open Jenkins dashboard.
2. Click **New Item**.
3. Enter a name (e.g., `first-java-webapp`) and select **Pipeline**, then click **OK**.
4. Scroll down to the **Pipeline** section.
5. Choose **Pipeline script** and paste the Jenkinsfile code above.
6. Click **Save**.
7. Click **Build Now** to trigger the pipeline.

---

## 🚀 Deployment Flow

1. Jenkins pulls code from GitHub.
2. Maven builds the `.war` file.
3. Jenkins deploys the `.war` to Tomcat using the Deploy to Container plugin.

---

## 🌐 Access the Application

Once deployed, your app will be available at:  
**http://<TOMCAT_EC2_IP>:8080/myapp**

<img width="1366" height="648" alt="Untitled" src="https://github.com/user-attachments/assets/f80791be-f8a1-495e-bcc1-3804da98ee59" />


