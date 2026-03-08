# Setup Instructions (Java Development Environment)

> **Note:** This document describes how to install and configure Java (JDK/JRE) on Windows.

---

## 1) Install Java (JDK)

1. Open a web browser and go to the official Oracle JDK download page or to an OpenJDK distribution such as [Eclipse Temurin](https://adoptium.net/).
2. Download the latest **JDK** for Windows (x64). Example: `OpenJDK 21`.
3. Run the installer and follow the prompts.

> ✅ **Tip:** Take a screenshot of the installer screen where it asks for the installation folder (e.g., `C:\Program Files\Java\jdk-21`).

---

## 2) Configure Environment Variables (Windows)

1. Open **Start** and search for **Environment Variables**. Select **Edit the system environment variables**.
2. In the **System Properties** window, click **Environment Variables...**.

### Add JAVA_HOME

- Under **System variables**, click **New...**.
- Set **Variable name** to `JAVA_HOME`.
- Set **Variable value** to the JDK install path (e.g., `C:\Program Files\Java\jdk-21`).

> ✅ Take a screenshot of the **Environment Variables** window showing `JAVA_HOME`.

### Update PATH

- In **System variables**, find and select the `Path` variable, then click **Edit...**.
- Add a new entry: `%JAVA_HOME%\bin`.

> ✅ Take a screenshot of the `Path` entries showing `%JAVA_HOME%\bin`.

---

## 3) Verify Installation

Open **PowerShell** or **Command Prompt** and run:

```powershell
java -version
javac -version
```

You should see output similar to:

```
java version "21" 2024-09-17
Java(TM) SE Runtime Environment (build 21+36)
Java HotSpot(TM) 64-Bit Server VM (build 21+36, mixed mode)

javac 21.0.1
```

> ✅ Take a screenshot of the output showing `java -version` and `javac -version`.

---

## 4) (Optional) Configure a Java IDE

Choose an IDE such as **IntelliJ IDEA**, **Eclipse**, or **VS Code** (with the Java Extension Pack). Follow the IDE-specific prompts to set the installed JDK as the project SDK.

---

## 5) Project Setup

1. Open the workspace folder in your IDE: `ClinicManagementSystem`.
2. Ensure `src` is recognized as a source folder (if using an IDE).

> ✅ If using VS Code, install the **Extension Pack for Java**.
