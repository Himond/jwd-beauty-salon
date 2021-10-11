package by.epam.litvinko.beautysalon.model.connection;

import by.epam.litvinko.beautysalon.exception.DatabaseConnectionException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


/**
 * The type Database connection pool.
 */
public class DatabaseConnectionPool {

    private static Logger logger = LogManager.getLogger(DatabaseConnectionPool.class);

    private static final int DEFAULT_POOL_SIZE = 5;
    private static DatabaseConnectionPool instance = new DatabaseConnectionPool();

    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceInitialized  = new AtomicBoolean(false);

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> busyConnections;


    private DatabaseConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        busyConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (DatabaseConnectionException e) {
                logger.error("Can't create connection with exception: ", e);
            }
        }
        if (freeConnections.isEmpty()) {
            logger.fatal("can't create connections, empty pool");
            throw new RuntimeException("can't create connections, empty pool");
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DatabaseConnectionPool getInstance() {
        if (!instanceInitialized.get()) {
            lock.lock();
            if (instance == null) {
                instance = new DatabaseConnectionPool();
                instanceInitialized.getAndSet(true);
            }
            lock.unlock();
        }
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            busyConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Something wrong with current thread", e);
        }
        return proxyConnection;
    }

    /**
     * Release connection boolean.
     *
     * @param connection the connection
     * @return the boolean
     */
    public boolean releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            logger.error("wild connection is detected");
            return false;
        }
        busyConnections.remove(connection);
        try {
            freeConnections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Something wrong with current thread", e);
        }
        return true;
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().closeConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread", e);
            } catch (SQLException e) {
                logger.error("Exception in connection close method", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Driver deregistration exception ", e);
            }
        });
    }


}
