import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;

public class db_conn {

    String url;
    String user;
    String password;
    Connection myConn;
    String error;

    public db_conn() {
        url = "jdbc:mysql://localhost:3306";
        user = "root";
        password = "Iondragonfly23!";
        try {
            myConn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            error = ("ERROR " + e.getLocalizedMessage());
        } finally {
        }
    }
}
