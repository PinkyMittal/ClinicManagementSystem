# MediTrack Clinic Management System

A simple Java console application demonstrating core Java concepts, object-oriented design, and basic persistence. This project is designed to satisfy learning requirements for:

- Java fundamentals (OOP, inheritance, encapsulation, polymorphism)
- JVM concepts and documentation
- Collections, generics, enums, and interfaces
- File I/O (CSV persistence)
- Design patterns (Singleton, Factory, Strategy)
- Console-based menu-driven UI

---

## ✅ Project Structure

- `src/main/java` - main application code
  - `com.airtribe.meditrack` - entry point and app logic
  - `entity` - domain model classes (Doctor, Patient, Appointment, Bill, etc.)
  - `service` - services for CRUD and business logic
  - `util` - utilities (validation, CSV I/O, data persistence, ID generation)
  - `constants` - shared constants
  - `enums` - enums for status and specialization
  - `exception` - custom exceptions
  - `interfaces` - `Searchable`, `Payable`
- `src/test/java` - manual test runner (`TestRunner.java`)
- `docs` - project deliverables (`Setup_Instructions.md`, `JVM_Report.md`)

---

## 🚀 Running the App

### Prerequisites

- Java 17+ (JDK)
- A terminal (PowerShell, Command Prompt)

### Run from the command line

```powershell
cd "c:\Users\MSUSERSL123\Desktop\ClinicManagementSystem\ClinicManagementSystem"
javac -d out -sourcepath src/main/java src/main/java/com/airtribe/meditrack/Main.java
java -cp out com.airtribe.meditrack.Main
```

To load persisted data from CSV files (if present):

```powershell
java -cp out com.airtribe.meditrack.Main --loadData
```

### Run the manual test runner

```powershell
javac -d out -sourcepath src/main/java src/test/java/com/airtribe/meditrack/test/TestRunner.java
java -cp out com.airtribe.meditrack.test.TestRunner
```

---

## 🧩 Features

- **CRUD operations** for patients and doctors
- **Appointments**: create, list, cancel, and status tracking
- **Billing**: bill generation with tax calculation (strategy pattern)
- **Persistence**: save/load doctors, patients, and appointments via CSV
- **Deep cloning** for `Appointment` and `Patient` demonstrating deep vs shallow copy
- **Immutable class**: `BillSummary`
- **Enums** for `Specialization` and `AppointmentStatus`
- **Singleton** ID generators (eager + lazy)
- **Rule-based AI helper** for doctor specialization recommendation

---

## 🗂️ Persistence

CSV files are stored under the `data/` folder (created automatically when saving). The default paths are in `com.airtribe.meditrack.constants.Constants`.

- `data/doctors.csv`
- `data/patients.csv`
- `data/appointments.csv`

---

## 📄 Documentation

- `docs/Setup_Instructions.md` — Java install/config instructions (with screenshots)
- `docs/JVM_Report.md` — JVM internals report

---

## 🛠️ Notes

- This project is designed as a teaching exercise and does not include production-grade error handling or concurrent access control.
- The console UI is intentionally lightweight and does not support advanced navigation.
