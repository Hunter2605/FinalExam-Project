import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String dbURL = "jdbc:mysql://localhost:3306/phone_repair'";
    private static String dbUsername = "root";
    private static String dbPassword = "";



    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }
}
