import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;

public class db_conn {

    String url;
    String user;
    String password;
    Connection connection;
    String error;

    public db_conn() {
        this.url = "jdbc:mysql://localhost:3306/employeedata";
        this.user = "root";
        this.password = "Iondragonfly23!";
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            this.error = ("ERROR " + e.getLocalizedMessage());
        } finally {
        }
    }
}
