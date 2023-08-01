package docrob;

import com.mysql.cj.jdbc.Driver;
import docrob.database.Config;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // create the connection
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Config.getUrl(),
                    Config.getUser(),
                    Config.getPassword()
            );
            String selectQuery = "SELECT * FROM albums";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            while (rs.next()) {
                System.out.println("Here's an album:");
                System.out.println("  id: " + rs.getLong("id"));
                System.out.println("  artist: " + rs.getString("artist"));
                System.out.println("  name: " + rs.getString("name"));
            }

        } catch(SQLException e) {
            System.out.println("An exception happened using the connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // ALWAYS close the connection
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("An exception happened closing the connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}