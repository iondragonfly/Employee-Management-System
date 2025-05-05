import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Administrator extends Employee {

    db_conn myConn = new db_conn();

    public Administrator() {
        super();
        this.isAdmin = true;
    }

    public void searchEmployeeOptions() {
        System.out.flush();
        System.out.println("\n\n\t\t\tSearch Employees");
        System.out.println("\n\tPlease select if you would like to search by: ");
        System.out.println("\t1. Employee ID" +
                "\n\t2. Name" +
                "\n\t3. D.O.B." +
                "\n\t4. SSN");
        Scanner input = new Scanner(System.in);
        int choice = Integer.parseInt(input.nextLine());
        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.println("Enter Employee ID: ");
                    int empid = Integer.parseInt(input.nextLine());
                    searchEmployee(empid, input);
                    break;
                case 2:
                    System.out.println("Enter Employee's First Name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter Employee's Last Name: ");
                    String lname = input.nextLine();
                    searchEmployee(fname, lname, input);
                    break;
                case 3:
                    System.out.println("Enter Employee's D.O.B. in the formatt YYYY-MM-DD: ");
                    String dobString = input.nextLine();
                    LocalDate dob = LocalDate.parse(dobString); // Parse Date entered
                    Date dobDate = Date.valueOf(dob); // Convert to Date object for SQL
                    searchEmployee(dobDate, input);
                    break;
                case 4:
                    System.out.println("Enter Employee's SSN in the formatt XXX-XX-XXXX: ");
                    String ssn = input.nextLine();
                    searchEmployee(ssn, input);
                    break;
                default:
                    System.out.println("Error Invalid Selection.");
                    break;
            }
            System.out.println("\tPlease select if you would like to search by: ");
            System.out.println("\t1. Employee ID" +
                    "\n\t2. Name" +
                    "\n\t3. D.O.B." +
                    "\n\t4. SSN");
            choice = Integer.parseInt(input.nextLine());

        }
    }

    public void searchEmployee(String fname, String lname, Scanner input) {
        try {
            String sqlcommand1 = "SELECT empid "
                    + "FROM employees " +
                    "WHERE Fname = \"" + fname + "\" AND Lname = \"" + lname + "\";";
            Statement myStmt = this.dbConnection.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            while (myRS1.next()) {
                Employee employee = new Administrator();
                employee.empID = myRS1.getInt("empid");
                employee.setEmployeeValues();
                employee.viewEmployeeData(employee.fname, employee.lname);
                updateOptions(input);
                updateEmployeeForm(employee, input);
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }

    }

    public void searchEmployee(int empid, Scanner input) {
        Employee employee = new Administrator();
        employee.empID = empid;
        employee.setEmployeeValues();
        employee.viewEmployeeData(employee.fname, employee.lname);
        updateOptions(input);
        updateEmployeeForm(employee, input);

    }

    public void searchEmployee(Date dob, Scanner input) {
        try {
            String sqlcommand1 = "SELECT empid "
                    + "FROM employees " +
                    "WHERE dob = " + dob + ";";
            Statement myStmt = this.dbConnection.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            if (myRS1.next()) {
                Employee employee = new Administrator();
                employee.empID = myRS1.getInt("empid");
                employee.setEmployeeValues();
                employee.viewEmployeeData(employee.fname, employee.lname);
                updateOptions(input);
                updateEmployeeForm(employee, input);
            } else {
                System.out.println("No Employee With matching Date of Birth found.");
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }
    }

    public void searchEmployee(String SSN, Scanner input) {
        try {
            String sqlcommand1 = "SELECT empid "
                    + "FROM employees " +
                    "WHERE SSN = \"" + SSN + "\";";
            Statement myStmt = this.dbConnection.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            while (myRS1.next()) {
                Employee employee = new Administrator();
                employee.empID = myRS1.getInt("empid");
                employee.setEmployeeValues();
                employee.viewEmployeeData(employee.fname, employee.lname);
                updateOptions(input);
                updateEmployeeForm(employee, input);
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getLocalizedMessage());
        }
    }

    public void updateOptions(Scanner input) {
        System.out.println("\n\t1.Update Employee Information" +
                "\t(Enter 0 to return to search)");
    }

    public void updateEmployeeForm(Employee employee, Scanner input) {
        employee.setEmployeeValues();
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Enter New Employee First Name: (enter if no change)");
                String newName = input.nextLine();
                if (newName.isEmpty()) {

                } else {
                    employee.fname = newName;
                }
                System.out.println("Enter New Last Name: (skip if no change)");
                String newLName = input.nextLine();
                if (newLName.isEmpty()) {

                } else {
                    employee.lname = newLName;
                }
                System.out.println("Enter New Username: (skip if no change)");
                String username = input.nextLine();
                if (username.equals("")) {

                } else {
                    employee.username = username;
                }
                System.out.println("Enter New Password: (skip if no change)");
                String password = input.nextLine();
                if (password.equals("")) {

                } else {
                    employee.password = password;
                }
                System.out.println("Enter New Email: (skip if no change)");
                String email = input.nextLine();
                if (email.equals("")) {

                } else {
                    employee.email = email;
                }
                System.out.println("Enter New Salary: (skip if no change)");
                Float newSalary;
                try {
                    newSalary = Float.parseFloat(input.nextLine());
                } catch (Exception e) {
                    newSalary = null;
                }
                if (newSalary == null) {

                } else {
                    employee.salary = newSalary;
                }
                System.out.println("Enter New Department Name: (skip if no change)");
                String division = input.nextLine();
                if (division.equals("")) {

                } else {
                    employee.department.name = division;
                }
                System.out.println("Enter New Job Title: (skip if no change)");
                String jobTitle = input.nextLine();
                if (jobTitle.equals("")) {

                } else {
                    employee.jobTitle.name = jobTitle;
                }
                UpdateEmployee updater = new UpdateEmployee();
                updater.updateEmployee(employee, myConn.connection);
                searchEmployee(employee.empID, input);
                break;

            case 0:
                searchEmployeeOptions();
                break;
            default:
                System.out.println("Invalid Entry.");
                updateOptions(input);
                break;

        }

    }

}
