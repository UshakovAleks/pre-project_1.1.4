package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    getConfig("db.url"), getConfig("db.user"), getConfig("db.password"));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return connection;
    }

    private static String getConfig(String config) {
        FileInputStream fis;
        String conf = null;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
            conf = properties.getProperty(config);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conf;
    }
}
