import java.sql.*;
import java.util.Scanner;

public class EmployeeManagementSystem {

    public static void main(String[] args) {
        System.out.println("\n\tEmployee Management System Login");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username = input.nextLine();
        System.out.println("Enter Password: ");
        String password = input.nextLine();
        try {
            db_conn myConn = new db_conn();
            String sqlcommand1 = "SELECT empid, username, dob, email, HireDate, Salary, password, isAdmin "
                    + " FROM employees " +
                    "WHERE username = '" + username + "';";
            Statement myStmt = myConn.connection.createStatement();
            ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
            while (myRS1.next()) {
                String userPassword = myRS1.getString("password");
                Boolean isAdmin = myRS1.getBoolean("isAdmin");
                int empID = myRS1.getInt("empid");
                if (password.equals(userPassword)) {
                    if (isAdmin) {
                        System.out.println("check");
                        Administrator user = new Administrator();
                        user.empID = empID;
                        user.setEmployeeValues();
                        user.mainMenu();
                    } else {
                        System.out.println("check2");
                        GeneralEmployee user = new GeneralEmployee();
                        user.empID = empID;
                        user.setEmployeeValues();
                        user.mainMenu();
                    }

                }
            }
            myConn.connection.close();
        } catch (Exception e) {
            System.out.println("Error in main " + e.getLocalizedMessage());
        }

        input.close();

    }
}
