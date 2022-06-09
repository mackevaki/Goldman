package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    private SQLiteConnection() {
    }
    private static final SQLiteConnection instance = new SQLiteConnection();

    public static SQLiteConnection getInstance() {
        return instance;
    }

    private Connection con;
    private String path = "db/mygoldman.db3";

    public Connection getConnection() {
        try {
            if (con == null) {
//                // динамическая регистрация драйвера SQLite
//                Class.forName("org.sqlite.JDBC").newInstance(); // необязательно для последних версий драйверов// необязательно для последних версий драйверов

                // создание подключение к базе данных по пути, указанному в урле
                String url = "jdbc:sqlite:" + path;

                con = DriverManager.getConnection(url);
            }
            return con;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            con.close();
            con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
