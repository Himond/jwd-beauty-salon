package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.dao.UserDao;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDate;

public class UserDaoTest {

    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private UserDao userDao = UserDao.getInstance();
    private User expected;
    private User newUser;

    @Before
    public void before(){

        connectionPool.initPool();
        connection = connectionPool.getConnection();

        userDao.setConnection(connection);

        User.Builder builder = User.newBuilder();
        builder.setID(3)
                .setRole(Role.CLIENT)
                .setUserName("client1")
                .setPassword("client1")
                .setEmail("client1@mail.ru")
                .setFirstName("client1")
                .setLastName("client1")
                .setIsActive(true)
                .setDateJoined(LocalDate.parse("2021-08-09"))
                .setPhoto(null);
        expected = builder.build();

        builder.setID(17)
                .setRole(Role.CLIENT)
                .setUserName("client3")
                .setPassword("client3")
                .setEmail("client3@mail.ru")
                .setFirstName("client3")
                .setLastName("client3")
                .setIsActive(true);
        newUser = builder.build();

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findUserByIdTest() throws DaoException {
        User actual = userDao.findById(3).get();
        Assert.assertEquals(actual, expected);;
    }

    @Test
    public void findAllUsersTest() throws DaoException {
        int actual = userDao.findAll().size();
        Assert.assertEquals(4, actual);
    }

    @Test
    public void createUserTest() throws DaoException {
        boolean actual = userDao.create(newUser);
        Assert.assertTrue(actual);
    }

    @Test
    public void deleteUserByEmailTest() throws DaoException {
        boolean actual = userDao.delete(newUser);
        Assert.assertTrue(actual);
    }

    @Test
    public void deleteUserByIdTest() throws DaoException {
        boolean actual = userDao.delete(16);
        Assert.assertTrue(actual);
    }

    @Test
    public void updateUserByIdTest() throws DaoException {

        newUser.setUserName("update1");
        newUser.setFirstName("update1");
        newUser.setLastName("update1");

        User actual = userDao.update(newUser).get();
        Assert.assertEquals(actual.getUserName(), newUser.getUserName());

    }

}
