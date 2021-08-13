package epam.by.litvinko.beautysalon.dao;

import epam.by.litvinko.beautysalon.entity.Entity;
import epam.by.litvinko.beautysalon.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<K extends Number, T extends Entity>{
    private static final Logger logger = LogManager.getLogger();
    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;
    public abstract Optional<T> findById(K id) throws DaoException;
    public abstract boolean insert(T entity) throws DaoException;
    public abstract boolean delete(T entity) throws DaoException;
    public abstract boolean delete(K id) throws DaoException;
    public abstract T update(T entity) throws DaoException;

    void closeStatement(Statement statement){
        try {
            if (statement != null) {
                statement.close();
            } else {
                logger.warn("Statement is null.");
            }

        } catch (SQLException e) {
            logger.error("Statement can't be closed.", e);
        }
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }

    void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Connection can't be moved back in pool.", e);

        }
    }

}
