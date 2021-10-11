package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.AbstractEntity;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * The type Abstract dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public abstract class AbstractDao<K extends Number, T extends AbstractEntity>{

    private static final Logger logger = LogManager.getLogger(AbstractDao.class);

    /**
     * The Connection.
     */
    protected Connection connection;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<T> findById(K id) throws DaoException;

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean create(T entity) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean delete(K id) throws DaoException;

    /**
     * Update optional.
     *
     * @param entity the entity
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<T> update(T entity) throws DaoException;

    /**
     * Close statement.
     *
     * @param statement the statement
     */
    public void closeStatement(Statement statement){
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

    /**
     * Sets connection.
     *
     * @param connection the connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Close connection.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
           logger.error("Connection can't be moved back in pool.", e);

        }
    }

}
