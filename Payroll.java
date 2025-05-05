import java.sql.*;
import java.util.Scanner;

public class Payroll {

	public Payroll() {
	}

	public void mainPayroll() {
		Scanner input = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n\t\t\tPayroll Reports Menu");
			System.out.println("1. View Full Payroll History ordered by Employee");
			System.out.println("2. View Full Payroll History ordered by Month");
			System.out.println("3. View Payroll for an Employee");
			System.out.println("4. View Payroll for a Department");
			System.out.println("5. View Payroll for a Job Title");
			System.out.println("6. Exit to Main Menu");
			System.out.print("Select Option: ");

			choice = input.nextInt();
			input.nextLine(); // flush

			switch (choice) {
				case 1:
					GetEmployeesPayroll.fullPayHistoryByEmployee(null);
					break;
				case 2:
					viewFullPayrollHistoryByMonth();
					break;
				case 3:
					viewPayrollForEmployee();
					break;
				case 4:
					viewPayrollForDepartment();
					break;
				case 5:
					viewPayrollForJobTitle();
					break;
				case 6:
					System.out.println("Returning to main menu...");
					break;
				default:
					System.out.println("Invalid option. Try again.");
			}
		} while (choice != 6);
	}

	public StringBuilder getPayByMonth(int empID, Connection myConn) {
		StringBuilder SB_output = new StringBuilder();
		String sql = "SELECT p.empid, p.pay_date, p.earnings, p.fed_tax, " +
				"p.fed_med, p.fed_SS, p.state_tax, p.retire_401k, p.health_care " +
				"FROM payroll p WHERE p.empid = ? ORDER BY p.pay_date";

		try (PreparedStatement pstmt = myConn.prepareStatement(sql)) {
			pstmt.setInt(1, empID);
			ResultSet rs = pstmt.executeQuery();

			SB_output.append("\tEMP ID\tPAY DATE\tGROSS\tFederal\tFedMed\tFedSS\tState\t401K\tHealthCare\n");
			while (rs.next()) {
				SB_output.append("\t").append(rs.getInt("empid")).append("\t");
				SB_output.append(rs.getDate("pay_date")).append("\t").append(rs.getDouble("earnings")).append("\t");
				SB_output.append(rs.getDouble("fed_tax")).append("\t").append(rs.getDouble("fed_med")).append("\t");
				SB_output.append(rs.getDouble("fed_SS")).append("\t").append(rs.getDouble("state_tax")).append("\t");
				SB_output.append(rs.getDouble("retire_401k")).append("\t").append(rs.getDouble("health_care"))
						.append("\n");
			}

		} catch (SQLException e) {
			SB_output.append("ERROR: ").append(e.getMessage());
		}

		return SB_output;
	}

	public static void viewPayrollForEmployee() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Employee ID to view payroll: ");
		int empID = input.nextInt();

		db_conn dbConn = new db_conn();
		Payroll payroll = new Payroll();
		StringBuilder output = payroll.getPayByMonth(empID, dbConn.connection);

		System.out.println(output.toString());
	}

	public void viewFullPayrollHistoryByMonth() {
		db_conn dbConn = new db_conn();
		String sql = "SELECT p.empid, e.Fname, e.Lname, p.pay_date, p.earnings " +
				"FROM payroll p JOIN employees e ON p.empid = e.empid " +
				"ORDER BY p.pay_date";

		try (Statement stmt = dbConn.connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("\nPayroll History Ordered by Month:\n");
			System.out.println("EmpID\tName\t\tPay Date\tEarnings");
			while (rs.next()) {
				System.out.printf("%d\t%s %s\t%s\t%.2f\n",
						rs.getInt("empid"),
						rs.getString("Fname"),
						rs.getString("Lname"),
						rs.getDate("pay_date").toString(),
						rs.getDouble("earnings"));
			}

		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void viewPayrollForDepartment() {
		db_conn dbConn = new db_conn();
		String sql = "SELECT d.dept_name, SUM(p.earnings) AS total_pay " +
				"FROM payroll p " +
				"JOIN employees e ON p.empid = e.empid " +
				"JOIN employee_division ed ON e.empid = ed.empid " +
				"JOIN division d ON ed.div_id = d.ID " +
				"GROUP BY d.dept_name";

		try (Statement stmt = dbConn.connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("\nPayroll Totals by Department:\n");
			System.out.println("Department\t\tTotal Pay");
			while (rs.next()) {
				System.out.printf("%-20s\t$%.2f\n",
						rs.getString("dept_name"),
						rs.getDouble("total_pay"));
			}

		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void viewPayrollForJobTitle() {
		db_conn dbConn = new db_conn();
		String sql = "SELECT jt.job_title, SUM(p.earnings) AS total_pay " +
				"FROM payroll p " +
				"JOIN employees e ON p.empid = e.empid " +
				"JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
				"JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
				"GROUP BY jt.job_title";

		try (Statement stmt = dbConn.connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("\nPayroll Totals by Job Title:\n");
			System.out.println("Job Title\t\tTotal Pay");
			while (rs.next()) {
				System.out.printf("%-20s\t$%.2f\n",
						rs.getString("job_title"),
						rs.getDouble("total_pay"));
			}

		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}