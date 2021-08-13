package epam.by.litvinko.beautysalon.connection;

import epam.by.litvinko.beautysalon.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class DatabaseConnectionPool {

    private static Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 5;
    private static DatabaseConnectionPool instance = new DatabaseConnectionPool();

    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceInitialized  = new AtomicBoolean(false);

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> busyConnections;


    private DatabaseConnectionPool() {
    }

    public void initPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        busyConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.put(proxyConnection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread" + e);
            } catch (DatabaseConnectionException e) {
                logger.error("can't create connection with exception: ", e);
            }
        }
        if (freeConnections.isEmpty()) {
            logger.fatal("can't create connections, empty pool");
            throw new RuntimeException("can't create connections, empty pool");
        }
    }

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

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            busyConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Something wrong with current thread" + e);
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            logger.error("wild connection is detected");
            throw new RuntimeException("wild connection is detected : " + connection);
        }
        busyConnections.remove(connection);
        try {
            freeConnections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Something wrong with current thread" + e);
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().closeConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread" + e);
            } catch (SQLException e) {
                logger.error("Exception in connection close method" + e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Driver deregistration exception " + driver + " " + e);
            }
        });
    }


}
