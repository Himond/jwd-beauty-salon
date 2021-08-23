package by.epam.litvinko.beautysalon.model.connection;

import by.epam.litvinko.beautysalon.exception.DatabaseConnectionException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {

    //private static Logger logger = LogManager.getLogger(ConnectionCreator.class);
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static final String DB_URL = "url";
    private static final String DB_DRIVER = "driver";
    private static final String RESOURCE = "db.properties";

    static {
        String driverName = null;
        System.out.println("static");
        try (InputStream inputStream = ConnectionCreator.class.getClassLoader().getResourceAsStream(RESOURCE)) {

            properties.load(inputStream);
            driverName = (String) properties.get(DB_DRIVER);
            System.out.println(driverName);
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            //logger.fatal("unable to register driver: " + driverName, e);
            throw new RuntimeException("unable to register driver: " + driverName, e);
        } catch (IOException e) {
            //logger.fatal("unable to load properties: ", e);
            throw new RuntimeException("unable to load properties: ", e);
        }
        DATABASE_URL = (String) properties.get(DB_URL);
        System.out.println(DATABASE_URL);
    }

    private ConnectionCreator() {
    }

    static Connection createConnection() throws DatabaseConnectionException {
        Connection connection;
        System.out.println("Create Connection");
        try {
            System.out.println(DATABASE_URL);
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            System.out.println("Create Connection");
        }catch (SQLException e){
            //logger.error("Unable to establish connection with URL = " + DATABASE_URL, e);
            throw new DatabaseConnectionException("Unable to establish connection with URL = " + DATABASE_URL, e);
        }
        return new ProxyConnection(connection);
    }
}
