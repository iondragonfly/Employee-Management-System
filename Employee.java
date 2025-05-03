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
    int jobTitleID;
    int empDivision;
    JobTitle jobTitle;
    db_conn dbConnection = new db_conn();

    public Employee() {

    }

    public Employee viewEmployeeData(String fname, String lname) {

    }

    public  signIn(String username, String password) {
        try {
            String sqlcommand1 = "SELECT p.empid, p.password" +
                    "FROM employees p " +
                    "WHERE p.username = " + username + " ";
            Statement myStmt = this.dbConnection.myConn.createStatement();
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
            this.dbConnection.myConn.close();
        } finally {
        }
    }

    public StringBuilder viewEmployeePayroll() {

    }

    public void mainMenu() {
        System.out.println("\t\tWelcome to the Employee Management System");
        System.out.println("Please Choose from the options below: ");
        if (empDivision == 0) {
            System.out.println("\t1. View Personal Profile Info\n" +
                    "\t2. View Payroll\n" +
                    "\t3. Search Employee\n" +
                    "\t4.");
        } else {

        }

    }

}
