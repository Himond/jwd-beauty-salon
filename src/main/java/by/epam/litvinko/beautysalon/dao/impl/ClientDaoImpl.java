package by.epam.litvinko.beautysalon.dao.impl;

import by.epam.litvinko.beautysalon.dao.AbstractDao;
import by.epam.litvinko.beautysalon.dao.ClientDao;
import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.dao.ColumnName.*;

public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {

    private static Logger logger = LogManager.getLogger();

    private static final String SELECT_ALL_CLIENT = "SELECT client.id, client.user_id, " +
            "client.phone, client.date_of_birthday, client.is_regular,  users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo " +
            "FROM client " +
            "JOIN users ON client.user_id = users.id " +
            "JOIN role ON users.role_id = role.id;";

    private static final String SELECT_CLIENT_BY_ID = "SELECT client.id, client.user_id, " +
            "client.phone, client.date_of_birthday, client.is_regular,  users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo " +
            "FROM client " +
            "JOIN users ON client.user_id = users.id " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE client.id = ?;";

    private static final String SELECT_CLIENT_BY_USER_ID = "SELECT client.id, client.user_id, " +
            "client.phone, client.date_of_birthday, client.is_regular,  users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo " +
            "FROM client " +
            "JOIN users ON client.user_id = users.id " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE client.user_id = ?;";

    private static final String INSERT_CLIENT = "INSERT INTO client(user_id, phone, date_of_birthday, is_regular) " +
            "VALUES (?, ?, ?, ?)";

    private static final String DELETE_CLIENT_BY_USER_ID = "DELETE FROM client WHERE user_id = ?;";
    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE id = ?;";

    private static final String UPDATE_CLIENT_BY_ID = "UPDATE client SET user_id = ?, " +
            "phone = ?, date_of_birthday = ?" +
            "WHERE id = ?;";

    private static final String UPDATE_CLIENT_REGULAR = "UPDATE client SET is_regular = ? WHERE user_id = ?";

    private static ClientDaoImpl instance;

    private ClientDaoImpl(){
    }

    public static ClientDaoImpl getInstance(){
        if(instance == null){
            instance = new ClientDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clientList = new ArrayList<>();
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CLIENT)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Client client = buildClient(resultSet);
                    clientList.add(client);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return clientList;
    }

    @Override
    public Optional<Client> findById(Integer id) throws DaoException {
        Client client = null;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }

        try (PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    client = buildClient(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(client);
    }


    @Override
    public boolean create(Client entity) throws DaoException {
        int result;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CLIENT)){
            statement.setInt(1, entity.getUserId());
            statement.setString(2, entity.getPhone());
            statement.setDate(3, Date.valueOf(entity.getDateOfBirthday()));
            statement.setBoolean(4, entity.isRegular());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return  result == 1;
    }

    @Override
    public boolean delete(Client entity) throws DaoException {
        int result;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }

        try(PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_USER_ID)) {
            statement.setInt(1, entity.getUserId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        int result;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }

        try(PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result == 1;
    }

    @Override
    public Optional<Client> update(Client entity) throws DaoException {
        Optional<Client> client;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        client = findById(entity.getId());
        if (client.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_BY_ID)){
                statement.setInt(1, entity.getUserId());
                statement.setString(2, entity.getPhone());
                statement.setDate(3, Date.valueOf(entity.getDateOfBirthday()));
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("User id = " + entity.getId() + " don't exist.");
        }
        client = findById(entity.getId());
        return client;
    }

    @Override
    public Optional<Client> findClientByUserId(Integer userId) throws DaoException {
        Client client = null;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_USER_ID)){
            statement.setInt(1, userId);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    client = buildClient(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(client);
    }

    @Override
    public void updateRegular(int userId, boolean isRegular) throws DaoException {
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        Optional<Client> user = findClientByUserId(userId);
        if (user.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_REGULAR)){
                statement.setBoolean(1, isRegular);
                statement.setInt(2, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("User id = " + userId + " don't exist.");
        }
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {
        Client client;
        Client.Builder builder = Client.newBuilder();
        builder.setUserId(resultSet.getInt(CLIENT_USERS_ID))
                .setPhone(resultSet.getString(CLIENT_PHONE))
                .setDateOfBirthday(LocalDate.parse(resultSet.getString(CLIENT_DATE_OF_BIRTHDAY)))
                .setIsRegular(resultSet.getBoolean(CLIENT_IS_REGULAR))
                .setID(resultSet.getInt(CLIENT_ID))
                .setRole(Role.valueOf(resultSet.getString(ROLE_ROLE).toUpperCase(Locale.ROOT)))
                .setUserName(resultSet.getString(USERS_USERNAME))
                .setPassword(resultSet.getString(USERS_PASSWORD))
                .setEmail(resultSet.getString(USERS_EMAIL))
                .setFirstName(resultSet.getString(USERS_FIRST_NAME))
                .setLastName(resultSet.getString(USERS_LAST_NAME))
                .setIsActive(resultSet.getBoolean(USERS_ACTIVE))
                .setDateJoined(LocalDate.parse(resultSet.getString(USERS_DATA_JOINED)))
                .setPhoto(resultSet.getBytes(USERS_PHOTO));
        client = builder.build();
        return client;
    }


}
