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
    float salary;
    Date hireDate;
    String ssn;
    int phoneNumber;
    Department department;
    JobTitle jobTitle;
    Boolean isAdmin;
    db_conn dbConnection = new db_conn();

    public Employee() {

    }

    public abstract void searchEmployeeOptions();

    public void viewEmployeeData(String fname, String lname) {
        // Display Employee Information
        // Give option to view personal payroll
        // Give option to return to mainMenu
        try {
            System.out.println("\n\t\t\tPersonal Information");
            System.out.println(" " + fname + " " + lname);
            System.out.println(" Username: " + this.username);
            System.out.println(" Password: " + this.password);
            System.out.println(" Email: " + this.email);
            System.out.println(" Hire Date: " + this.hireDate.toLocalDate());
            System.out.println(" Salary: $" + this.salary);
            try {
                System.out.println(" D.O.B.\t" + this.dob.toLocalDate());
            } catch (Exception e) {
                System.out.println(" No D.O.B in Employee file.");
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
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
            String sqlcommand1 = "SELECT Fname, Lname, username, dob, email, HireDate, Salary, password, isAdmin, SSN "
                    + "FROM employees " +
                    "WHERE empid = " + this.empID + ";";
            Statement myStmt = this.dbConnection.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            while (myRS1.next()) {
                this.fname = myRS1.getString("Fname");
                this.lname = myRS1.getString("Lname");
                this.username = myRS1.getString("username");
                this.password = myRS1.getString("password");
                this.email = myRS1.getString("email");
                this.dob = myRS1.getDate("dob");
                this.hireDate = myRS1.getDate("HireDate");
                this.ssn = myRS1.getString("SSN");
                this.salary = myRS1.getFloat("Salary");

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
                    "\t4. Add Employee\n" +
                    "\t5. Exit\n");
            Scanner input = new Scanner(System.in);
            int option = Integer.parseInt(input.nextLine());
            while (option != 5) {

                if (option == 1) {
                    this.viewEmployeeData(this.fname, this.lname);
                } else if (option == 2) {
                    Payroll payroll = new Payroll();
                    payroll.mainPayroll();
                } else if (option == 3) {
                    this.searchEmployeeOptions();
                } else if (option == 4) {
                    UpdateEmployee updater = new UpdateEmployee();
                    updater.addEmployeeScreen(input, dbConnection.connection);
                } else {
                    System.out.println("Invalid Selection: Please enter the number for your choice.");
                }

                System.out.println("Select another option: ");
                System.out.println("\t1. View Personal Profile Info" +
                        "\t\t2. View Payroll" +
                        "\t\t3. Search Employee" +
                        "\t\t4. Add Employee" +
                        "\t\t5. Exit\n");
                option = Integer.parseInt(input.nextLine());

            }
        } else {

        }

    }

}
