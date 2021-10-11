package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.model.dao.ClientDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;

/**
 * The type Client dao.
 */
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {

    private static Logger logger = LogManager.getLogger(ClientDaoImpl.class);

    private static final String SELECT_ALL_CLIENT = "SELECT client.id, client.user_id, " +
            "client.phone, client.date_of_birthday, client.is_regular,  users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo, client_account.money " +
            "FROM client " +
            "JOIN users ON client.user_id = users.id " +
            "JOIN client_account ON client.id = client_account.client_id " +
            "JOIN role ON users.role_id = role.id;";

    private static final String SELECT_CLIENT_BY_ID = "SELECT client.id, client.user_id, " +
            "client.phone, client.date_of_birthday, client.is_regular,  users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo, client_account.money " +
            "FROM client " +
            "JOIN users ON client.user_id = users.id " +
            "JOIN client_account ON client.id = client_account.client_id " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE client.id = ?;";

    private static final String SELECT_CLIENT_BY_USER_ID = "SELECT client.id, client.user_id, " +
            "client.phone, client.date_of_birthday, client.is_regular,  users.id, role.role, " +
            "users.username, users.password, users.email, users.first_name, users.last_name, " +
            "users.is_active, users.data_joined, users.photo, client_account.money " +
            "FROM client " +
            "JOIN users ON client.user_id = users.id " +
            "JOIN client_account ON client.id = client_account.client_id " +
            "JOIN role ON users.role_id = role.id " +
            "WHERE client.user_id = ?;";

    private static final String INSERT_CLIENT = "INSERT INTO client(user_id, phone) " +
            "VALUES (?, ?)";

    private static final String INSERT_ACCOUNT = "INSERT INTO client_account(client_id) " +
            "VALUES (?)";

    private static final  String TOP_UP_ACCOUNT = "UPDATE client_account SET card_number = ?, money = money + ? " +
            "WHERE client_id = ?;";

    private static final  String PAY_FOR_ORDER = "UPDATE client_account SET money = money - ? " +
            "WHERE client_id = ?;";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE id = ?;";

    private static final String UPDATE_CLIENT_BY_ID = "UPDATE client SET phone = ?, date_of_birthday = ?" +
            "WHERE id = ?;";

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clientList = new ArrayList<>();
        Connection connection = super.connection;
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
        boolean result;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CLIENT,  Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, entity.getUserId());
            statement.setString(2, entity.getPhone());
            result = statement.executeUpdate() == 1;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int clientId = generatedKeys.getInt(1);
                entity.setId(clientId);
            } else {
                throw new SQLException("Can not make new client.");
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
        try(PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<Client> update(Client entity) throws DaoException {
        Optional<Client> optionalClient;
        Connection connection = super.connection;
        optionalClient = findClientByUserId(entity.getUserId());
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_BY_ID)){
                statement.setString(1, entity.getPhone());
                statement.setDate(2, Date.valueOf(entity.getDateOfBirthday()));
                statement.setInt(3, client.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("Client id = " + entity.getId() + " don't exist.");
        }
        optionalClient = findClientByUserId(entity.getId());
        return optionalClient;
    }

    @Override
    public Optional<Client> findClientByUserId(Integer id) throws DaoException {
        Client client = null;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_USER_ID)){
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
    public boolean createAccount(int clientId) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT)) {
            statement.setInt(1, clientId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<Client> topUpAccount(int clientId, String cardNumber, String amount) throws DaoException {
        Optional<Client> optionalClient;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(TOP_UP_ACCOUNT)){
            statement.setString(1, cardNumber);
            statement.setBigDecimal(2, BigDecimal.valueOf(Long.parseLong(amount)));
            statement.setInt(3, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
        }
        optionalClient = findById(clientId);
        return optionalClient;
    }

    @Override
    public Optional<Client> payForOrder(int clientId, BigDecimal price) throws DaoException {
        Optional<Client> optionalClient;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(PAY_FOR_ORDER)){
            statement.setBigDecimal(1, price);
            statement.setInt(2, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
        }
        optionalClient = findById(clientId);
        return optionalClient;
    }


    private Client buildClient(ResultSet resultSet) throws SQLException {
        Client client;
        Client.Builder builder = Client.newBuilder();
        builder.setUserId(resultSet.getInt(CLIENT_USERS_ID))
                .setPhone(resultSet.getString(CLIENT_PHONE))
                .setIsRegular(resultSet.getBoolean(CLIENT_IS_REGULAR))
                .setCurrentAccount(resultSet.getBigDecimal(CLIENT_MONEY))
                .setId(resultSet.getInt(CLIENT_ID))
                .setRole(Role.valueOf(resultSet.getString(ROLE_ROLE).toUpperCase(Locale.ROOT)))
                .setUserName(resultSet.getString(USERS_USERNAME))
                .setPassword(resultSet.getString(USERS_PASSWORD))
                .setEmail(resultSet.getString(USERS_EMAIL))
                .setFirstName(resultSet.getString(USERS_FIRST_NAME))
                .setLastName(resultSet.getString(USERS_LAST_NAME))
                .setIsActive(resultSet.getBoolean(USERS_ACTIVE))
                .setDateJoined(LocalDate.parse(resultSet.getString(USERS_DATA_JOINED)))
                .setPhoto(resultSet.getString(USERS_PHOTO));
        client = builder.build();
        if (resultSet.getString(CLIENT_DATE_OF_BIRTHDAY)!= null){
            client.setDateOfBirthday(LocalDate.parse(resultSet.getString(CLIENT_DATE_OF_BIRTHDAY)));
        }
        return client;
    }



}
