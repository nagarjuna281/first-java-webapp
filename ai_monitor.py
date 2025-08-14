import requests

APP_URL = "http://54.167.80.96:8080/my-responsive-webapp-1.0.0-SNAPSHOT"

try:
    response = requests.get(APP_URL, timeout=5)
    if response.status_code == 200:
        print("✅ App is up and running.")
    else:
        print(f"⚠️ App returned status code: {response.status_code}")
except Exception as e:
    print(f"❌ Failed to reach app: {e}")
