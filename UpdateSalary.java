import java.sql.*;

public class UpdateSalary {

    public UpdateSalary() {
    }

    public StringBuilder updateSalaryByEmpID(int empID, Connection myConn) {
        // add code to select employee by empID and update salary
        // return sucess or error message
    }

    public StringBuilder updateSalaryByRange(Float minSalary, Float maxSalary, Float prcntIncrease, Connection myConn) {
        // select all employees with salary in range
        // Change salary by prcntIncrease (could be negative)
        // return success or error message
    }

    public StringBuilder updateSalaryByDept(int deptID, Float prcntIncrease, Connection myConn) {
        // select all employees in department
        // Change salary by prcntIncrease (could be negative)
        // return success or error message
    }

}
