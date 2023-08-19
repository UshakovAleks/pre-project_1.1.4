package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {

    private Connection connection;
    private SessionFactory sessionFactory;
    private static Util instance;
    private Util() {
        this.connection = getCon();
        this.sessionFactory = getSession();
    }
    public static Util getUtil() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }

    private static Connection getCon() {
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

    private static SessionFactory getSession(){
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");

        prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");

        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "Valera83797");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        prop.setProperty("show_sql", "true");
        prop.setProperty("hibernate.hbm2ddl.auto","update");

        SessionFactory sessionFactory = new Configuration().addProperties(prop).buildSessionFactory();
        return  sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
