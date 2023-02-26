Title: Client Scheduling Application
Author: Dominic Payer
Student ID: 010482349
Contact Information: contact@email.com
Version No: 1.0
As of: 02/26/2023

IDE: IntelliJ IDEA 2022.1.2 (Community Edition)
JDK: Java FJK 17.0.5
FX: JavaFX FXML 17.0.1
DB: MySQL Workbench v.8.0.32
Driver: MySQL Connector/J v.8.0.32

How to run:
-run the main method in the Application.java file
-follow prompts from the GUI to login
-test login has username "test" and password "test"
-logins and logouts are tracked in the "login_activity.txt" file (created if not existing)
-from the Dashboard, you can view appointments, customers, or reports via navigation
-Customers: can add, update, or delete (appointments for a deleted customer are deleted)
-Appointments: can add, update, or delete
-Reports: a variety of reports/metrics are available as described below

Reports (available on the Reports Page):
-the total number of appointments, filterable by month and type
-the busiest month (has the most appointments)
-the busiest Contact (has the most appointments)
-generate a schedule for a selected Contact (directs to the Contact Schedule page)

