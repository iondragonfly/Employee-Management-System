import java.sql.*;
import java.util.Scanner;

public class UpdateEmployee {

    public UpdateEmployee() {

    }

    public void updateEmployee(Employee employee, Connection myConn) {
        try {
            Statement myStmt = myConn.createStatement();
            String sqlcommand1 = ("UPDATE employees " +
                    "SET username = \"" + employee.username +
                    "\", password = \"" + employee.password +
                    "\", Fname = \"" + employee.fname +
                    "\", Lname = \"" + employee.lname +
                    "\", email = \"" + employee.email +
                    "\", Salary = " + (int) employee.salary +
                    " Where empid = " + employee.empID + ";");
            System.out.println(sqlcommand1);
            myStmt.executeUpdate(sqlcommand1);
            System.out.println("Employee \"" + employee.fname + " " + employee.lname + " (" + employee.empID
                    + ")\" Updated Successfully!");
        } catch (Exception e) {
            System.out.println("ERROR while Updating:  " + e.getLocalizedMessage());
        }
        System.out.println("\nPress Enter to clear console screen...\n");
        Scanner myScann = new Scanner(System.in);
        String str = myScann.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // Add Code for updating empolyee by passing an employee object
        // Use empID to select employee in db, and update the rest of the information
        // return success or error message
    }

    public void addEmployeeScreen(Scanner input, Connection myConn) {
        System.out.flush();
        System.out.println("\t\t\tAdd Employee");
        System.out.println("\nEnter Employee's First Name: ");
        String fname = input.nextLine();
        System.out.println("\nEnter Employee's Last Name: ");
        String lname = input.nextLine();
        System.out.println("\nEnter Employee's Email: ");
        String email = input.nextLine();
        System.out.println("\nEnter Employee's SSN (in the formatt XXX-XX-XXXX): ");
        String SSN = input.nextLine();
        System.out.println("\nEnter Employee's Salary: ");
        int salary = Integer.parseInt(input.nextLine());
        Employee employee = new GeneralEmployee();
        employee.fname = fname;
        employee.lname = lname;
        employee.email = email;
        employee.ssn = SSN;
        employee.salary = salary;
        addEmployee(employee, myConn);

    }

    public void addEmployee(Employee employee, Connection myConn) {
        try {
            Statement myStmt = myConn.createStatement();
            String sqlcommand1 = ("Insert into employees (Fname, Lname, email, SSN, Salary, isAdmin) " +
                    "Values (\"" + employee.fname +
                    "\", \"" + employee.lname +
                    "\", \"" + employee.email +
                    "\", \"" + employee.ssn +
                    "\", " + (int) employee.salary + ", false);");
            System.out.println(sqlcommand1);
            myStmt.executeUpdate(sqlcommand1);
            String sqlcommand2 = "SELECT empid" +
                    "FROM employees " +
                    "WHERE email = '" + employee.email + "';";
            Statement myStmt2 = myConn.createStatement();
            ResultSet myRS1 = myStmt2.executeQuery(sqlcommand2);
            if (myRS1.next()) {
                employee.empID = myRS1.getInt("empid");
            }

            System.out.println("Employee \"" + employee.fname + " " + employee.lname +
                    "\" successfully added to the database!\n" +
                    employee.fname + "'s Employee ID is " + employee.empID);
        } catch (Exception e) {
            System.out.println("ERROR while Updating:  " + e.getLocalizedMessage());
        }
        System.out.println("\nPress Enter to clear console screen...\n");
        Scanner myScann = new Scanner(System.in);
        String str = myScann.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // add code for adding new employee to database
        // empID should auto-increment so empID should not be set
        // return success or error message
    }

}
