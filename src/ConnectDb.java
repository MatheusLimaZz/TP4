import java.sql.*;

public class ConnectDb {
    private static final String URL = "jdbc:postgresql://localhost:5432/tp4";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public static Connection ConnectionDb(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
