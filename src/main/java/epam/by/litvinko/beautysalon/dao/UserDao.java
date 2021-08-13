package epam.by.litvinko.beautysalon.dao;

import epam.by.litvinko.beautysalon.connection.DatabaseConnectionPool;
import epam.by.litvinko.beautysalon.entity.Role;
import epam.by.litvinko.beautysalon.entity.User;
import epam.by.litvinko.beautysalon.exception.DaoException;
import epam.by.litvinko.beautysalon.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDao extends AbstractDao<Long, User>{


    private static Logger logger = LogManager.getLogger();

    private static final String GET_USER_BY_EMAIL
            = "SELECT id, role_id, username, password, email, first_name, last_name, " +
            "is_active, data_joined, photo" +
            "FROM users " +
            "WHERE users.email = ?;";


    private static UserDao instance;
    private static final DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();


    private UserDao(){
    }

    public static UserDao getInstance(){
        if(instance == null){
            instance = new UserDao();
        }
        return instance;
    }

    public Optional<User> signIn(String username, String password) throws DaoException{
        final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
        Optional<User> optionalUser;
        User user;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection wasn't set.");
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_USER_BY_EMAIL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // FIXME: 13.08.2021 
                User.Builder builder = User.newBuilder();
                builder.setID(resultSet.getLong("id"))
                        .setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)))
                        .setUserName(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDateJoined(LocalDate.parse(resultSet.getString("data_joined")))
                        .setPhoto(resultSet.getBytes("photo"));
                user = builder.build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Optional.empty();

    }


    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean insert(User entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public User update(User entity) throws DaoException {
        return null;
    }
}
