import java.sql.*;
import java.util.Scanner;

public abstract class Employee {

    int empID;
    String fname;
    String lname;
    String email;
    Date dob;
    String username;
    String password;
    int salary;
    Date hireDate;
    String ssn;
    int phoneNumber;
    int empDivision;
    JobTitle jobTitle;
    Boolean isAdmin;
    db_conn dbConnection = new db_conn();

    public Employee() {

    }

    public void viewEmployeeData(String fname, String lname) {
        // Display Employee Information
        // Give option to view personal payroll
        // Give option to return to mainMenu
        try {
            String sqlcommand1 = "SELECT p.empid, p.username, p.dob, p.email, p.HireDate, p.Salary, p.password" +
                    "FROM employees p " +
                    "WHERE p.fname = " + fname + " AND p.Lname =  " + lname + " ";
            Statement myStmt = this.dbConnection.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            int empID = myRS1.getInt("p.empid");
            String email = myRS1.getString("p.email");
            String username = myRS1.getString("p.username");
            String password = myRS1.getString("p.password");
            Date dob = myRS1.getDate("p.dob");
            Date hireDate = myRS1.getDate("p.HireDate");
            Float salary = myRS1.getFloat("p.Salary");
            System.out.println("\n\t\t\tPersonal Information");
            System.out.println(" " + fname + " " + lname);
            System.out.println(" Username: " + username);
            System.out.println(" Password: " + password);
            System.out.println(" Email: " + email);
            System.out.println(" Hire Date: " + hireDate.toLocalDate());
            System.out.println(" Salary: " + salary);
            System.out.println(" D.O.B.\t" + dob.toLocalDate());
        } catch (SQLException e) {

        } finally {

        }
    }

    public signIn(String username, String password) {
        //Currently Unused could replace sign-in code in main
        //Would require some refactoring
        try {
            String sqlcommand1 = "SELECT empid, password" +
                    "FROM employees p " +
                    "WHERE username = '" + username + "';";
            Statement myStmt = this.dbConnection.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            String userPassword = myRS1.getString("p.password");
            if (password.equals(userPassword)) {
                setEmployeeValues();
                mainMenu();
            }
        } catch (Error | SQLException e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }
        return false;
    }

    public void signOut() {
        System.out.println("Signing Out...");
        try {
            this.dbConnection.connection.close();
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }
    }

    public StringBuilder viewEmployeePayroll() {

    }

    public void setEmployeeValues() {
        try {
            String sqlcommand1 = "SELECT username, dob, email, HireDate, Salary, password, isAdmin, SSN "
                    + "FROM employees " +
                    "WHERE empid = " + this.empID + ";";
            Statement myStmt = this.dbConnection.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            while (myRS1.next()) {
                this.username = myRS1.getString("username");
                this.email = myRS1.getString("email");
                this.dob = myRS1.getDate("dob");
                this.hireDate = myRS1.getDate("HireDate");
                this.ssn = myRS1.getString("SSN");

                // set more values
            }
        } catch (SQLException e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        } finally {

        }
    }

    public void mainMenu() {
        System.out.println("\t\t\tWelcome to the Employee Management System");
        System.out.println("Please Choose from the options below: ");
        if (this.isAdmin) {
            System.out.println("\t1. View Personal Profile Info\n" +
                    "\t2. View Payroll\n" +
                    "\t3. Search Employee\n" +
                    "\t4. ");
        } else {

        }

    }

}
