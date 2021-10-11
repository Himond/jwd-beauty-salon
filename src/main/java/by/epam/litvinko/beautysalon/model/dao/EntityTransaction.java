package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Entity transaction.
 */
public class EntityTransaction {

    private static Logger logger = LogManager.getLogger(EntityTransaction.class);
    private Connection connection;

    /**
     * Init transaction.
     *
     * @param dao  the dao
     * @param daos the daos
     * @throws DaoException the dao exception
     */
    public void initTransaction(AbstractDao dao, AbstractDao... daos) throws DaoException {
        if (connection == null) {
            connection = DatabaseConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Unable to install autocommit for connection: ", e);
            throw new DaoException("Unable to install autocommit for connection: ", e);
        }
        dao.setConnection(connection);
        for (AbstractDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    /**
     * Init.
     *
     * @param dao the dao
     */
    public void init(AbstractDao dao) {
        if (connection == null) {
            connection = DatabaseConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

    /**
     * End transaction.
     *
     * @throws DaoException the dao exception
     */
    public void endTransaction() throws DaoException {
        if (connection == null) {
            throw new DaoException("Connection has been lost.");
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Unable to install autocommit for connection: ", e);
            throw new DaoException("Unable to install autocommit for connection: ", e);
        }
        DatabaseConnectionPool.getInstance().releaseConnection(connection);
        connection = null;
    }

    /**
     * End.
     *
     * @throws DaoException the dao exception
     */
    public void end() throws DaoException {
        if (connection == null) {
            throw new DaoException("Connection has been lost.");
        }
        DatabaseConnectionPool.getInstance().releaseConnection(connection);
        connection = null;
    }

    /**
     * Commit.
     *
     * @throws DaoException the dao exception
     */
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("Failed to commit: ", e);
            throw new DaoException("Failed to commit: ", e);
        }
    }

    /**
     * Rollback.
     *
     * @throws DaoException the dao exception
     */
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Failed to rollback: ", e);
            throw new DaoException("Failed to rollback: ", e);
        }
    }
}
