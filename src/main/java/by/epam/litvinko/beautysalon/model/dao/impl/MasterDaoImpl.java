package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.model.dao.MasterDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;

/**
 * The type Master dao.
 */
public class MasterDaoImpl extends AbstractDao<Integer, Master> implements MasterDao {

    private static Logger logger = LogManager.getLogger(MasterDaoImpl.class);

    private static final String SELECT_ALL_MASTER = "SELECT master.id, master.user_id, " +
            "position.position, master.description, users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo " +
            "FROM master " +
            "JOIN position ON master.position_id = position.id " +
            "JOIN users ON master.user_id = users.id " +
            "JOIN role ON users.role_id = role.id;";


    private static final String SELECT_MASTER_BY_ID = "SELECT master.id, master.user_id, " +
            "position.position, master.description, users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo " +
            "FROM master " +
            "JOIN position ON master.position_id = position.id " +
            "JOIN users ON master.user_id = users.id " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE master.id = ?;";

    private static final String SELECT_MASTER_BY_USER_ID = "SELECT master.id, master.user_id, " +
            "position.position, master.description, users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo " +
            "FROM master " +
            "JOIN position ON master.position_id = position.id " +
            "JOIN users ON master.user_id = users.id " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE master.user_id = ?;";

    private static final String SELECT_ALL_POSITION = "SELECT id, position " +
            "FROM position;";

    private static final String INSERT_MASTER = "INSERT INTO master(user_id, position_id, description) " +
            "VALUES (?, ?, ?)";

    private static final String DELETE_MASTER_BY_ID = "DELETE FROM master WHERE id = ?;";

    private static final String UPDATE_CLIENT_BY_ID = "UPDATE master SET user_id = ?, " +
            "position_id = ?, description = ?" +
            "WHERE id = ?;";


    @Override
    public List<Master> findAll() throws DaoException {
        List<Master> masterList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MASTER)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Master master = buildMaster(resultSet);
                    masterList.add(master);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return masterList;
    }

    @Override
    public Optional<Master> findById(Integer id) throws DaoException {
        Master master = null;
        Connection connection = super.connection;

        try (PreparedStatement statement = connection.prepareStatement(SELECT_MASTER_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    master = buildMaster(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(master);
    }


    @Override
    public boolean create(Master entity) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_MASTER)){
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getPosition().getId());
            statement.setString(3, entity.getDescription());
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return  result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(DELETE_MASTER_BY_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<Master> update(Master entity) throws DaoException {
        Optional<Master> master;
        Connection connection = super.connection;
        master = findById(entity.getId());
        if (master.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_BY_ID)){
                statement.setInt(1, entity.getUserId());
                statement.setInt(2, entity.getPosition().getId());
                statement.setString(3,entity.getDescription());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("Master id = " + entity.getId() + " don't exist.");
        }
        master = findById(entity.getId());
        return master;
    }

    @Override
    public Optional<Master> findMasterByUserId(Integer id) throws DaoException {
        Master master = null;
        Connection connection = super.connection;

        try (PreparedStatement statement = connection.prepareStatement(SELECT_MASTER_BY_USER_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    master = buildMaster(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(master);
    }

    @Override
    public List<Position> allPosition() throws DaoException {
        List<Position> positionList = new ArrayList<>();
        Position position;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_POSITION)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    position = Position.valueOf(resultSet.getString(POSITION_POSITION).toUpperCase());
                    positionList.add(position);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return positionList;
    }

    private Master buildMaster(ResultSet resultSet) throws SQLException {
        Master master;
        Master.Builder builder = Master.newBuilder();
        builder.setUserId(resultSet.getInt(MASTER_USERS_ID))
                .setPosition(Position.valueOf(resultSet.getString(POSITION_POSITION).toUpperCase(Locale.ROOT)))
                .setDescription(resultSet.getString(MASTER_DESCRIPTION))
                .setId(resultSet.getInt(MASTER_ID))
                .setRole(Role.valueOf(resultSet.getString(ROLE_ROLE).toUpperCase(Locale.ROOT)))
                .setUserName(resultSet.getString(USERS_USERNAME))
                .setPassword(resultSet.getString(USERS_PASSWORD))
                .setEmail(resultSet.getString(USERS_EMAIL))
                .setFirstName(resultSet.getString(USERS_FIRST_NAME))
                .setLastName(resultSet.getString(USERS_LAST_NAME))
                .setIsActive(resultSet.getBoolean(USERS_ACTIVE))
                .setDateJoined(LocalDate.parse(resultSet.getString(USERS_DATA_JOINED)))
                .setPhoto(resultSet.getString(USERS_PHOTO));
        master = builder.build();
        return master;
    }



}
