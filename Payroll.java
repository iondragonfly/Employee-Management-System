import java.sql.*;
import java.util.Scanner;

public class Payroll {

	public Payroll() {

	}

	public void mainPayroll() {
		System.out.flush();
		System.out.println("");
		System.out.println("\t\t\tView Payroll Reports");
		System.out.println("Select Option: ");
		System.out.println("\t1. View Full Payroll History ordered by Employee");
		System.out.println("\t2. View Full Payroll History ordered by Month");
		System.out.println("\t3. View Payroll For Employee");
		System.out.println("\t4. View Payroll For Department");
		System.out.println("\t5. View Payroll For Job Title");
		System.out.println("6. Exit to Main Menu");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		while (choice != 6) {
			switch (choice) {
				case 1:
					GetEmployeesPayroll.fullPayHistoryByEmployee(null);
					break;
				case 3:
					this.viewPayrollForEmployee();

				default:
					break;
			}
		}

	}

	public StringBuilder getPayByMonth(int empID, Connection myConn) {
		StringBuilder SB_output = new StringBuilder("");
		String sqlcommand1 = "SELECT p.empid, p.pay_date, p.earnings, p.fed_tax, " +
				"p.fed_med,p.fed_SS,p.state_tax,p.retire_401k,p.health_care  " +
				"FROM payroll p " +
				"WHERE p.empid = " + empID + " " +
				"ORDER BY p.pay_date;";
		try {
			Statement myStmt = myConn.createStatement();

			SB_output.append("\tEMP ID\tPAY DATE\tGROSS\tFederal\tFedMed\tFedSS\tState\t401K\tHealthCare\n");
			ResultSet myRS1 = myStmt.executeQuery(sqlcommand1);
			while (myRS1.next()) {
				SB_output.append("\t" + myRS1.getString("p.empid") + "\t");
				SB_output.append(myRS1.getDate("p.pay_date") + "\t" + myRS1.getDouble("p.earnings") + "\t");
				SB_output.append(myRS1.getDouble("p.fed_tax") + "\t" + myRS1.getDouble("p.fed_med") + "\t");
				SB_output.append(myRS1.getDouble("p.fed_SS") + "\t" + myRS1.getDouble("p.state_tax") + "\t");
				SB_output.append(myRS1.getDouble("p.retire_401K") + "\t" + myRS1.getDouble("p.health_care") + "\n");
			}
		} catch (SQLException e) {
			System.out.println("ERROR " + e.getLocalizedMessage());
		} finally {
		}

		return (SB_output);
	}

	public static void viewPayrollForEmployee() {
		System.out.flush();
		System.out.println("null");
	}

}
