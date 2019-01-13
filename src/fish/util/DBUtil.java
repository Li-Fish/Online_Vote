package fish.util;

import java.sql.*;

public class DBUtil {
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Connection con;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=emls", "sa", "123");
        return con;
    }

    public static void disconnect(Connection con, Statement sta) throws SQLException {
        if (sta != null) {
            sta.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public static void disconnect(Connection con, Statement sta, ResultSet rst) throws SQLException {
        if (rst != null) {
            rst.close();
        }
        disconnect(con, sta);
    }
}
