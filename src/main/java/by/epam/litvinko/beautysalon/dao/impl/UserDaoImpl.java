package by.epam.litvinko.beautysalon.dao.impl;

import by.epam.litvinko.beautysalon.dao.AbstractDao;
import by.epam.litvinko.beautysalon.dao.UserDao;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.litvinko.beautysalon.dao.ColumnName.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    private static Logger logger = LogManager.getLogger();

    private static final String SELECT_ALL_USER = "SELECT users.id, role.role, users.username, " +
            "users.password, users.email, users.first_name, " +
            "users.last_name, users.is_active, users.data_joined, users.photo " +
            "FROM users " +
            "JOIN role ON users.role_id = role.id;";

    private static final String SELECT_USER_BY_ROLE = "SELECT users.id, role.role, users.username, " +
            "users.password, users.email, users.first_name, " +
            "users.last_name, users.is_active, users.data_joined, users.photo " +
            "FROM users " +
            "JOIN role ON users.role_id = role.id WHERE role.role = ?;";

    private static final String SELECT_USER_BY_ID = "SELECT users.id, role.role, users.username, " +
            "users.password, users.email, users.first_name, " +
            "users.last_name, users.is_active, users.data_joined, users.photo " +
            "FROM users " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE users.id = ?;" ;

    private static final String INSERT_USER = "INSERT INTO users(role_id, username, password, " +
            "email, first_name, last_name, is_active, data_joined, photo) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";

    private static final String UPDATE_USER_BY_ID = "UPDATE users SET username = ?, " +
            "password = ?, email = ?, first_name = ?, last_name = ?, is_active = ?, data_joined = ?, photo = ? " +
            "WHERE id = ?;";

    private static final String SELECT_USER_BY_LOGIN = "SELECT users.id, role.role, users.username, " +
            "users.password, users.email, users.first_name, " +
            "users.last_name, users.is_active, users.data_joined, users.photo " +
            "FROM users " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE users.username = ?;" ;

    private static final String SELECT_USER_BY_EMAIL = "SELECT users.id, role.role, users.username, " +
            "users.password, users.email, users.first_name, " +
            "users.last_name, users.is_active, users.data_joined, users.photo " +
            "FROM users " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE users.email = ?;" ;


    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }

        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    User user = buildUser(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        User user = null;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }

        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                     user = buildUser(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public boolean create(User entity) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)){
            statement.setInt(1, entity.getRole().getId());
            statement.setString(2, entity.getUserName());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getFirstName());
            statement.setString(6, entity.getLastName());
            statement.setBoolean(7, entity.isActive());
            statement.setDate(8, Date.valueOf(LocalDate.now()));
            statement.setBytes(9, entity.getPhoto());
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
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }

        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)){
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<User> update(User entity) throws DaoException {
        Optional<User> user;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        user = findById(entity.getId());
        if (user.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)){
                statement.setString(1, entity.getUserName());
                statement.setString(2, entity.getPassword());
                statement.setString(3, entity.getEmail());
                statement.setString(4, entity.getFirstName());
                statement.setString(5, entity.getLastName());
                statement.setBoolean(6, entity.isActive());
                statement.setDate(7, Date.valueOf(entity.getDateJoined()));
                statement.setBytes(8, entity.getPhoto());
                statement.setInt(9, entity.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("User id = " + entity.getId() + " don't exist.");
        }
        user = findById(entity.getId());
        return user;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        User user = null;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)){
            statement.setString(1, login);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    user = buildUser(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        User user = null;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
            statement.setString(1, email);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    user = buildUser(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAllByRoll(Role role) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try(PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ROLE)) {
            statement.setString(1, role.getRole());
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    User user = buildUser(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return users;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user;
        User.Builder builder = User.newBuilder();
        builder.setID(resultSet.getInt(USERS_ID))
                .setRole(Role.valueOf(resultSet.getString(ROLE_ROLE).toUpperCase(Locale.ROOT)))
                .setUserName(resultSet.getString(USERS_USERNAME))
                .setPassword(resultSet.getString(USERS_PASSWORD))
                .setEmail(resultSet.getString(USERS_EMAIL))
                .setFirstName(resultSet.getString(USERS_FIRST_NAME))
                .setLastName(resultSet.getString(USERS_LAST_NAME))
                .setIsActive(resultSet.getBoolean(USERS_ACTIVE))
                .setDateJoined(LocalDate.parse(resultSet.getString(USERS_DATA_JOINED)))
                .setPhoto(resultSet.getBytes(USERS_PHOTO));
        user = builder.build();
        return user;
    }


}
