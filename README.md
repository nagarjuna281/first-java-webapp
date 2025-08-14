# 🚀 Java Web App CI/CD Pipeline with Jenkins & Tomcat

This project demonstrates a complete CI/CD pipeline for a Java web application using Jenkins, Maven, and Apache Tomcat. It includes automated build, deployment, and post-deployment monitoring using a Python script.

---

## 📦 Tech Stack

- Java (Maven-based web app)
- Jenkins (Pipeline as Code)
- Apache Tomcat (Remote deployment)
- GitHub (Source control)
- Python (Post-deployment monitoring)

---

## 📸 Project Overview

![Pipeline Overview](images/pipeline-overview.png)
*Diagram showing Jenkins stages: Clone → Build → Deploy → Monitor*

---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

---

### 2. Jenkins Configuration

#### 🔹 Install Required Plugin

- **Deploy to Container Plugin**

#### 🔹 Create Jenkins Pipeline Job

Use the following `Jenkinsfile`:

```groovy
pipeline {
    agent any

    environment {
        WAR_FILE = 'target/my-responsive-webapp-1.0.0-SNAPSHOT.war'
        TOMCAT_URL = 'http://<TOMCAT_IP>:8080'
        CREDENTIALS_ID = 'tomcat-credentials'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/your-username/your-repo-name.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                deploy adapters: [
                    tomcat9(credentialsId: CREDENTIALS_ID, url: TOMCAT_URL)
                ],
                war: WAR_FILE
            }
        }

        stage('Run AI Monitor') {
            steps {
                sh 'python3 ai_monitor.py || echo "AI monitor failed or not present"'
            }
        }
    }

    post {
        success {
            echo '✅ Deployment completed successfully!'
        }
        failure {
            echo '❌ Deployment failed. Check logs for details.'
        }
    }
}
```

---

### 3. Tomcat Configuration

#### 🔹 Enable Remote Deployment

Edit `tomcat-users.xml`:

```xml
<role rolename="manager-script"/>
<user username="admin" password="admin123" roles="manager-script"/>
```

Restart Tomcat and verify access at:

```
http://<TOMCAT_IP>:8080/manager/text
```

---

### 4. Jenkins Credentials

Add Jenkins credentials:

- **Kind**: Username and Password
- **ID**: `tomcat-credentials`
- **Username**: `admin`
- **Password**: `admin123`

---

## 🧠 AI Monitoring Script

### `ai_monitor.py`

```python
import requests

APP_URL = "http://<TOMCAT_IP>:8080/my-responsive-webapp-1.0.0-SNAPSHOT"

try:
    response = requests.get(APP_URL, timeout=5)
    if response.status_code == 200:
        print("✅ App is up and running.")
    else:
        print(f"⚠️ App returned status code: {response.status_code}")
except Exception as e:
    print(f"❌ Failed to reach app: {e}")
```

---

## 📊 Monitoring Output

View results in Jenkins:

- Go to your pipeline job
- Click the latest build
- Click **Console Output**
- Look for monitoring results in the **Run AI Monitor** stage

<img width="1182" height="587" alt="image" src="https://github.com/user-attachments/assets/123d8fbd-68fb-4258-9eea-a1bdd75f59bf" />

---

## ✅ Final Verification

Visit your deployed app:

```
http://<TOMCAT_IP>:8080/my-responsive-webapp-1.0.0-SNAPSHOT
```

<img width="1187" height="685" alt="image" src="https://github.com/user-attachments/assets/89fb0dfb-a7b7-4a17-9944-e078c332144d" />


---

## 📌 Next Steps

- Add unit tests to the pipeline
- Integrate Slack or email notifications
- Dockerize the app for container-based deployment
- Add rollback logic if monitoring fails

---

## 📄 License

This project is licensed under the MIT License.
```

---

## 🖼️ Recommended Images to Add

Place these images in a folder called `images/` inside your repo:

| Image File | Description |
|------------|-------------|
| `pipeline-overview.png` | A diagram showing Jenkins stages (Clone → Build → Deploy → Monitor) |
| `jenkins-console-output.png` | Screenshot of Jenkins console output showing monitoring results |
| `webapp-screenshot.png` | Screenshot of your deployed Java web app in the browser |

---

Would you like me to generate a sample pipeline diagram or help you capture the screenshots?
