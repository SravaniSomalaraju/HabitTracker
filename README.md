# Habit Tracker & Productivity System

A console-based Java application that helps users build positive habits, track daily progress, and measure productivity using a MySQL relational database.  
This project demonstrates Java fundamentals, JDBC, SQL, and layered architecture (DAO + Service + Model).

---

## 🚀 Features

- Add new habits  
- View all habits  
- Mark a habit as completed for the day  
- View today’s productivity score  
- Generate a 7-day productivity report  
- Delete habits  
- Data stored in MySQL using **foreign key relationships**  
- Uses clean architecture (DAO, Service, Model, Main)

---

## 🛠️ Tech Stack

- **Java (Core + JDBC)**
- **MySQL Database**
- **MySQL Workbench**
- **Eclipse IDE**
- **JDBC MySQL Connector**

---

## 📁 Project Structure

HabitTracker/
└── src/
├── model/
│ └── Habit.java
│
├── dao/
│ ├── DBConnection.java
│ └── HabitDAO.java
│
├── service/
│ └── HabitService.java
│
└── main/
└── HabitTrackerApp.java

sql
Copy code

---

## 🗄️ Database Setup (MySQL)

Run the following SQL commands in MySQL Workbench:

```sql
CREATE DATABASE IF NOT EXISTS habitdb;
USE habitdb;

CREATE TABLE IF NOT EXISTS habits (
  habit_id INT AUTO_INCREMENT PRIMARY KEY,
  habit_name VARCHAR(100),
  created_on DATE
);

CREATE TABLE IF NOT EXISTS habit_log (
  log_id INT AUTO_INCREMENT PRIMARY KEY,
  habit_id INT,
  log_date DATE,
  status BOOLEAN,
  FOREIGN KEY (habit_id) REFERENCES habits(habit_id)
);
✔️ Table: habits
Stores all habits.

✔️ Table: habit_log
Stores daily completion logs linked with each habit using a Foreign Key.

⚙️ How to Run the Project
1. Clone or download the project into Eclipse
Go to:
File → Import → Existing Java Project → Select Folder

2. Add MySQL JDBC Connector
Download the connector:
mysql-connector-j-x.x.x.jar

In Eclipse:
Right-click project → Build Path → Configure Build Path → Classpath → Add External JARs

3. Update your DBConnection.java
java
Copy code
private static final String URL = "jdbc:mysql://localhost:3306/habitdb";
private static final String USER = "root";     // your MySQL username
private static final String PASSWORD = "____"; // your password
4. Run the application
Open:

css
Copy code
main → HabitTrackerApp.java
Right-click → Run As → Java Application

📌 Usage Guide (Menu Explanation)
1️⃣ Add Habit
Enter any habit name.
Example:

yaml
Copy code
Enter habit name: Drinking water
2️⃣ View All Habits
Shows list of habits with their IDs.

3️⃣ Mark Habit As Completed
Choose a valid habit ID from option 2.
A record is stored in habit_log.

4️⃣ Show Today’s Productivity Score
Displays percentage =

scss
Copy code
(completed habits today / total habits) × 100
5️⃣ Show Last 7 Days Report
Shows productivity score for each day.

6️⃣ Delete Habit
Deletes the habit + its logs (foreign key).

📐 UML Diagram (Text)
css
Copy code
Habit
 ├─ id : int
 ├─ name : String
 ├─ createdOn : LocalDate

HabitDAO
 ├─ addHabit(Habit)
 ├─ getAllHabits()
 ├─ markHabitForDate()
 ├─ getCompletedHabitsOnDate()
 ├─ deleteHabit()

HabitService
 ├─ addHabit()
 ├─ viewHabits()
 ├─ markHabitCompletedToday()
 ├─ showTodayProductivity()
 ├─ showLast7DaysReport()

HabitTrackerApp
 └─ main()
🗃️ ER Diagram (Text)
markdown
Copy code
habits
-------------------------
habit_id (PK)
habit_name
created_on


habit_log
-------------------------
log_id (PK)
habit_id (FK)
log_date
status
Relationship:
1 habit → many log entries

🎥 Video Script (If You Want to Add a Demo Video)
pgsql
Copy code
Hi, this is my Java Habit Tracker Project.

It allows users to add habits, track daily progress, and view productivity scores using MySQL. 
I built it using Java, JDBC, DAO design pattern, and a relational database with foreign keys.

The app provides options to add habits, view habits, mark completion, view productivity, 
generate 7-day reports, and delete habits.

This project shows my understanding of Java backend development, database design, 
SQL queries, and clean architecture.