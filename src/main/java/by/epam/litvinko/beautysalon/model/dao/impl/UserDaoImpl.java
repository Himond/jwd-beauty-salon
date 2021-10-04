package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.model.dao.UserDao;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

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
            "email, first_name, last_name, data_joined, photo) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";

    private static final String UPDATE_USER_BY_ID = "UPDATE users SET email = ?, first_name = ?, last_name = ? " +
            "WHERE id = ?;";

    private static final String UPDATE_USER_PHOTO_BY_ID = "UPDATE users SET photo = ? " +
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

    private static final String SET_PASSWORD_BY_ID = "UPDATE Users SET password = ? WHERE id = ?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = super.connection;
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
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER,  Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, entity.getRole().getId());
            statement.setString(2, entity.getUserName());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getFirstName());
            statement.setString(6, entity.getLastName());
            statement.setDate(7, Date.valueOf(LocalDate.now()));
            statement.setString(8, entity.getPhoto());
            result = statement.executeUpdate() == 1;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                entity.setId(userId);
            } else {
                throw new SQLException("Can not make new user.");
            }
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
        user = findById(entity.getId());
        if (user.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)){
                statement.setString(1, entity.getEmail());
                statement.setString(2, entity.getFirstName());
                statement.setString(3, entity.getLastName());
                statement.setInt(4, entity.getId());
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
    public void updateUserPhotoById(String userId, String photo) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PHOTO_BY_ID)){
            statement.setString(1, photo);
            statement.setInt(2, Integer.parseInt(userId));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
        }

    }

    @Override
    public List<User> findAllByRoll(Role role) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = super.connection;
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

    @Override
    public void setPasswordById(Integer id, String password) throws DaoException {
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(SET_PASSWORD_BY_ID)){
                statement.setString(1, password);
                statement.setInt(2, id);
                statement.executeUpdate();
        } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user;
        User.Builder builder = User.newBuilder();
        builder.setId(resultSet.getInt(USERS_ID))
                .setRole(Role.valueOf(resultSet.getString(ROLE_ROLE).toUpperCase(Locale.ROOT)))
                .setUserName(resultSet.getString(USERS_USERNAME))
                .setPassword(resultSet.getString(USERS_PASSWORD))
                .setEmail(resultSet.getString(USERS_EMAIL))
                .setFirstName(resultSet.getString(USERS_FIRST_NAME))
                .setLastName(resultSet.getString(USERS_LAST_NAME))
                .setIsActive(resultSet.getBoolean(USERS_ACTIVE))
                .setDateJoined(LocalDate.parse(resultSet.getString(USERS_DATA_JOINED)))
                .setPhoto(resultSet.getString(USERS_PHOTO));
        user = builder.build();
        return user;
    }


}
