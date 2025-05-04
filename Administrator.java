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
        System.out.println("\n\t\t\tSearch Employees");
        System.out.println("\tPlease select if you would like to search by: ");
        System.out.println("\t1. Employee ID" +
                "\n\t2. Name" +
                "\n\t3. D.O.B." +
                "\n\t4. SSN");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.println("Enter Employee ID: ");
                    int empid = input.nextInt();
                    searchEmployee(empid);
                    break;
                case 2:
                    System.out.println("Enter Employee's First Name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter Employee's Last Name: ");
                    String lname = input.nextLine();
                    searchEmployee(fname, lname);
                    break;
                case 3:
                    System.out.println("Enter Employee's D.O.B. in the formatt YYYY-MM-DD: ");
                    String dobString = input.nextLine();
                    LocalDate dob = LocalDate.parse(dobString); // Parse Date entered
                    Date dobDate = Date.valueOf(dob); // Convert to Date object for SQL
                    searchEmployee(dobDate);
                    break;
                case 4:
                    System.out.println("Enter Employee's SSN in the formatt XXX-XX-XXXX: ");
                    String ssn = input.nextLine();
                    searchEmployee(ssn);
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
            choice = input.nextInt();
        }
    }

    public void searchEmployee(String fname, String lname) {

    }

    public void searchEmployee(int empid) {
        Employee employee = new Administrator();
        employee.empID = empid;
        employee.setEmployeeValues();
        employee.viewEmployeeData(employee.fname, employee.lname);
        updateOptions();
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        // while

    }

    public void searchEmployee(Date dob) {

    }

    public void searchEmployee(String SSN) {

    }

}
