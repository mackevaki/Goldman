package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    private SQLiteConnection(){
    }

    private static final SQLiteConnection instance = new SQLiteConnection();

    public static SQLiteConnection getInstance() {
        return instance;
    }

    private Connection con;
    private String path = "db/goldman..db3";

    public Connection getConnection() {
        try {
            if (con == null) {
//                // динамическая регистрация драйвера SQLite
//                Class.forName("org.sqlite.JDBC").newInstance(); // необязательно для последних версий драйверов

                // создание подключения к базе данных по пути, указанному в урле
                String url = "jdbc:sqlite:" + path;

                con = DriverManager.getConnection(url);

                return con;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void closeConnection() {
        try {
            con.close();
            con = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
